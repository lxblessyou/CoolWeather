package android.coolweather.user.coolweather.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.coolweather.user.coolweather.R;
import android.coolweather.user.coolweather.activity.ActivityMain;
import android.coolweather.user.coolweather.activity.ActivityWeather;
import android.coolweather.user.coolweather.db.City;
import android.coolweather.user.coolweather.db.County;
import android.coolweather.user.coolweather.db.Province;
import android.coolweather.user.coolweather.util.HttpUtil;
import android.coolweather.user.coolweather.util.JsonUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by user on 2017-12-09.
 */

public class FragmentChooseArea extends Fragment {
    // 常量
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    // View 控件
    private ProgressDialog progressDialog;
    private TextView tv_bar_title;
    private Button btn_bar_back;
    private ListView lv_choose_area;
    // 列表适配器
    private ArrayAdapter<String> adapter;
    // 字符串数据列表
    private List<String> dataList = new ArrayList<>();
    /**
     * 省份对象列表
     */
    private List<Province> provinceList;
    /**
     * 城市对象列表
     */
    private List<City> cityList;
    /**
     * 县对象列表
     */
    private List<County> countyList;
    /**
     * 已选中的省份对象
     */
    private Province selectedProvince;
    /**
     * 已选中的城市对象
     */
    private City selectedCity;
    /**
     * 当前选中的级别
     */
    private int currentLevel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_area, container, false);
        // 初始化控件
        initView(view);
        // 初始化适配器
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, dataList);
        // 设置适配器
        lv_choose_area.setAdapter(adapter);
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        tv_bar_title = view.findViewById(R.id.tv_bar_title);
        btn_bar_back = view.findViewById(R.id.btn_bar_back);
        lv_choose_area = view.findViewById(R.id.lv_choose_area);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Activity 创建完成后初始化列表成员点击事件
        // 1.列表成员点击事件
        lv_choose_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (currentLevel) {
                    case LEVEL_PROVINCE:
                        selectedProvince = provinceList.get(position);
                        // 如果当前显示的列表等级为省份则读取城市列表
                        queryCities();
                        break;
                    case LEVEL_CITY:
                        selectedCity = cityList.get(position);
                        // 如果当前显示的列表等级为城市则读取县列表
                        queryCounty();
                        break;
                    case LEVEL_COUNTY:
                        String weatherId = countyList.get(position).getWeatherId();
                        // 通过 instanceof 判断 getActivity() 属于哪个 Activity
                        if (getActivity() instanceof ActivityMain) {
                            // 如果是在县列表里点击的成员，则跳转到 ActivityWeather 界面
                            Intent intent = new Intent(getActivity(), ActivityWeather.class);
                            intent.putExtra("weather_id", weatherId);
                            startActivity(intent);
                            getActivity().finish();
                        } else if (getActivity() instanceof ActivityWeather) {
                            // 如果是 ActivityWeather 实例
                            ActivityWeather activity = (ActivityWeather) getActivity();
                            activity.dl_drawer.closeDrawers();
                            activity.srl_refresh.setRefreshing(true);
                            activity.requestWeather(weatherId);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        // 2.回退按钮点击事件
        btn_bar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (currentLevel) {
                    case LEVEL_COUNTY:
                        // 查询城市
                        queryCities();
                        break;
                    case LEVEL_CITY:
                        // 查询省份
                        queryProvinces();
                        break;
                    default:
                        break;
                }
            }
        });
        queryProvinces();
    }

    /**
     * 查询全国所有的省，优先从数据库查询，如果没有查询到再去服务器上查询
     */
    private void queryProvinces() {
        // 1.初始化其他控件该有状态
        tv_bar_title.setText("中国");
        btn_bar_back.setVisibility(View.GONE);
        // 2.从数据库查询数据对象列表
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0) {
            // 1.如果从数据库中查出数据
            dataList.clear();   // 清空数据集合
            // 2.遍历对象列表，把从对象中获取的名称添加到数据集
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
            }
            // 3.通知列表适配器数据改变
            adapter.notifyDataSetChanged();
            // 4.复位（重定位列表）
            lv_choose_area.setSelection(0);
            // 5.修改当前列表等级
            currentLevel = LEVEL_PROVINCE;
        } else {
            // 1.初始化 URL
            String urlPath = "http://guolin.tech/api/china";
            // 从服务器获取数据
            queryFromServer(urlPath, "province");
        }
    }

    /**
     * 查询全国所有的城市，优先从数据库查询，如果没有查询到再去服务器上查询
     */
    private void queryCities() {
        tv_bar_title.setText(selectedProvince.getProvinceName());
        btn_bar_back.setVisibility(View.VISIBLE);
        cityList = DataSupport.where("provinceid = ?", String.valueOf(selectedProvince.getProvinceCode())).find(City.class);
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            lv_choose_area.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            String urlPath = "http://guolin.tech/api/china/" + selectedProvince.getProvinceCode();
            // 从服务器获取数据
            queryFromServer(urlPath, "city");
        }
    }

    /**
     * 查询全国所有的县，优先从数据库查询，如果没有查询到再去服务器上查询
     */
    private void queryCounty() {
        tv_bar_title.setText(selectedCity.getCityName());
        btn_bar_back.setVisibility(View.VISIBLE);
        countyList = DataSupport.where("cityid = ? ", String.valueOf(selectedCity.getCityCode())).find(County.class);
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            lv_choose_area.setSelection(0);
            currentLevel = LEVEL_COUNTY;
        } else {
            String urlPath = "http://guolin.tech/api/china/" + selectedProvince.getProvinceCode() + "/" + selectedCity.getCityCode();
            // 从服务器获取数据
            queryFromServer(urlPath, "county");
        }
    }

    /**
     * 根据传入的地址和类型从服务器上查询省市县数据
     *
     * @param urlPath URL
     * @param type    查询类型
     */
    private void queryFromServer(String urlPath, final String type) {
        // 1.显示进度条对话框
        showProgressDialog();
        // 2.用OkHttp做网络操作
        HttpUtil.sendOkHttpRequest(urlPath, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 通过 runOnUiThread() 方法回到主线程处理程序
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(getContext(), "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                boolean result = false;
                if ("province".equals(type)) {
                    result = JsonUtil.handleProvinceResponse(responseText);
                } else if ("city".equals(type)) {
                    result = JsonUtil.handleCityResponse(responseText, selectedProvince.getProvinceCode());
                } else if ("county".equals(type)) {
                    result = JsonUtil.handleCountyResponse(responseText, selectedCity.getCityCode());
                }
                if (result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 1.关闭进度条对话框
                            closeProgressDialog();
                            if ("province".equals(type)) {
                                queryProvinces();
                            } else if ("city".equals(type)) {
                                queryCities();
                            } else if ("county".equals(type)) {
                                queryCounty();
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * 关闭进度条对话框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /**
     * 显示进度条对话框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

}
