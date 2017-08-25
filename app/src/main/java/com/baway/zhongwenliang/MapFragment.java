package com.baway.zhongwenliang;



public class MapFragment{
//        OnGetPoiSearchResultListener, OnGetSuggestionResultListener,
//        View.OnClickListener {
//
//    View view;
//    private MapView mMapView;
//    private StringBuffer sb;
//    private LocationClient mLocationClient = null;
//    private BDLocationListener mBDlocationListener = new MyBDlocationListener();
//    public BaiduMap mBaiduMap;
//    public BitmapDescriptor mCurrentMarker;
//    public com.baidu.mapapi.map.MyLocationConfiguration.LocationMode mCurrentMode;
//    protected InfoWindow mInfoWindow;
//    public String registrationID;
//    private double mCurrentLatitude;// 经度
//    private double mCurrentLongitude;// 纬度
//
//    public static LatLng cenpt;
//
//    /**
//     * 周边检索相关
//     */
//    private PoiSearch mPoiSearch = null;
//    private SuggestionSearch mSuggestionSearch = null;
//    /**
//     * 搜索关键字输入窗口
//     */
//    private AutoCompleteTextView keyWorldsView = null;
//    private ArrayAdapter<String> sugAdapter = null;
//    private int load_Index = 0;
//    private String tempStr;
//    private EditText etSearchKey;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        SDKInitializer.initialize(getActivity().getApplicationContext());
//        view = inflater.inflate(R.layout.fragment_map, null);
//        init();
//        return view;
//    }
//
//    private void init() {
//        // TODO Auto-generated method stub
//        initViews();
//        initObj();
//    }
//
//    protected void initViews() {
//        mMapView = (MapView) view.findViewById(R.id.bmapView);
//        mBaiduMap = mMapView.getMap();
//        mLocationClient = new LocationClient(getActivity()
//                .getApplicationContext());
//        etSearchKey = (EditText) view.findViewById(R.id.et_poi_map);
//    }
//
//    protected void initObj() {
//        mLocationClient.registerLocationListener(mBDlocationListener);
//        initLocation();
//        if (CommUtil.isNetConnected(getActivity())) {
//            mLocationClient.start();
//        }else {
//            Toast.makeText(getActivity(), "无网络", Toast.LENGTH_SHORT).show();
//        }
//        view.findViewById(R.id.btn_search_map).setOnClickListener(this);
//        view.findViewById(R.id.btn_hospital_map).setOnClickListener(this);
//        view.findViewById(R.id.btn_wc_map).setOnClickListener(this);
//        view.findViewById(R.id.btn_park_map).setOnClickListener(this);
//    }
//
//    /**
//     * 定位参数
//     */
//    private void initLocation() {
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
//        int span = 1000;
//        option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
//        option.setOpenGps(true);// 可选，默认false,设置是否使用gps
//        option.setLocationNotify(false);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
//        option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        option.setIgnoreKillProcess(false);// 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
//        option.setEnableSimulateGps(false);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
//        mLocationClient.setLocOption(option);
//    }
//
//    /***
//     * 实现BDLocationListener接口，重写onReceiveLocation处理接收的定位数据
//     *
//     * @author mishaoshuai
//     *
//     */
//    class MyBDlocationListener implements BDLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // 开启定位图层
//            mBaiduMap.setMyLocationEnabled(true);
//            // 构造定位数据
//            MyLocationData locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(0).latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//            // 设置定位数据
//            mBaiduMap.setMyLocationData(locData);
//            // 定位图标
//            mCurrentMarker = BitmapDescriptorFactory
//                    .fromResource(R.drawable.map);
//            MyLocationConfiguration config = new MyLocationConfiguration(
//                    com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.NORMAL,
//                    true, mCurrentMarker);
//            mBaiduMap.setMyLocationConfigeration(config);
//            mBaiduMap.getUiSettings().setScrollGesturesEnabled(true);
//
//            // 纬度
//            mCurrentLongitude = location.getLongitude();
//            // 经度
//            mCurrentLatitude = location.getLatitude();
//            /**
//             * 定位关键代码
//             */
//            keyLocation();
//
//            // // 当不需要定位图层时关闭定位图层
//            // mBaiduMap.setMyLocationEnabled(false);
//            // Receive Location
//            sb = new StringBuffer(256);
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//                sb.append("\ndescribe : ");
//                sb.append("gps定位成功");
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//                sb.append("\ndescribe : ");
//                sb.append("网络定位成功");
//            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                sb.append("\ndescribe : ");
//                sb.append("离线定位成功，离线定位结果也是有效的");
//            } else if (location.getLocType() == BDLocation.TypeServerError) {
//                sb.append("\ndescribe : ");
//                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                sb.append("\ndescribe : ");
//                sb.append("网络不同导致定位失败，请检查网络是否通畅");
//            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                sb.append("\ndescribe : ");
//                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//            }
//            sb.append("\nlocationdescribe : ");
//            sb.append(location.getLocationDescribe());// 位置语义化信息
//            List<Poi> list = location.getPoiList();// POI数据
//            if (list != null) {
//                sb.append("\npoilist size = : ");
//                sb.append(list.size());
//                for (Poi p : list) {
//                    sb.append("\npoi= : ");
//                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
//                }
//            }
//            sb.append("\nregistrationID:");
//            sb.append(registrationID);
//            mLocationClient.stop();// 关闭定位，否则会一直定位在你当前位置
//        }
//    }
//
//    /**
//     * 定位
//     */
//    public void keyLocation() {
//        cenpt = new LatLng(mCurrentLatitude, mCurrentLongitude);// 参数分别是：经度，纬度
//        // 定义地图状态
//        MapStatus mMapStatus = new MapStatus.Builder().target(cenpt).zoom(14)
//                .build();
//        // 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
//        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory
//                .newMapStatus(mMapStatus);
//        // 改变地图状态
//        mBaiduMap.setMapStatus(mMapStatusUpdate);
//    }
//
//    /**
//     * 周边检索
//     */
//    private void PoiSearch(String str) {
//        // TODO Auto-generated method stub
//        mPoiSearch = PoiSearch.newInstance();
//        mPoiSearch.setOnGetPoiSearchResultListener(this);
//        mSuggestionSearch = SuggestionSearch.newInstance();
//        mSuggestionSearch.setOnGetSuggestionResultListener(this);
//        keyWorldsView = (AutoCompleteTextView) view
//                .findViewById(R.id.et_poi_map);
//        sugAdapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_dropdown_item_1line);
//        keyWorldsView.setAdapter(sugAdapter);
//
//        // EditText editCity = (EditText) view.findViewById(R.id.et_city_map);
//
//        // 搜索附近poi
//        mPoiSearch.searchNearby(new PoiNearbySearchOption().keyword(str)
//                .location(cenpt).pageNum(3).radius(10000));
//        // 城市内关键字搜索poi
//        // mPoiSearch.searchInCity((new PoiCitySearchOption())
//        // .city(editCity.getText().toString())
//        // .keyword(editSearchKey.getText().toString())
//        // .pageNum(load_Index));
//
//        /**
//         * 当输入关键字变化时，动态更新建议列表
//         */
//        keyWorldsView.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1,
//                                          int arg2, int arg3) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2,
//                                      int arg3) {
//                if (cs.length() <= 0) {
//                    return;
//                }
//                // String city = ((EditText)
//                // view.findViewById(R.id.et_city_map))
//                // .getText().toString();
//                // /**
//                // * 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
//                // */
//                // mSuggestionSearch
//                // .requestSuggestion((new SuggestionSearchOption())
//                // .keyword(cs.toString()).city(city));
//            }
//        });
//    }
//
//    @Override
//    public void onGetSuggestionResult(SuggestionResult res) {
//        // TODO Auto-generated method stub
//
//        if (res == null || res.getAllSuggestions() == null) {
//            return;
//        }
//        sugAdapter.clear();
//        for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
//            if (info.key != null)
//                sugAdapter.add(info.key);
//        }
//        sugAdapter.notifyDataSetChanged();
//
//    }
//
//    @Override
//    public void onGetPoiDetailResult(PoiDetailResult result) {
//        // TODO Auto-generated method stub
//
//        if (result.error != SearchResult.ERRORNO.NO_ERROR) {
//            Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT)
//                    .show();
//        } else {
//            Toast.makeText(getActivity(),
//                    result.getName() + ": " + result.getAddress(),
//                    Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    @Override
//    public void onGetPoiResult(PoiResult result) {
//        // TODO Auto-generated method stub
//
//        if (result == null
//                || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
//            Toast.makeText(getActivity(), "未找到结果", Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//            mBaiduMap.clear();
//            PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
//            mBaiduMap.setOnMarkerClickListener(overlay);
//            overlay.setData(result);
//            overlay.addToMap();
//            overlay.zoomToSpan();
//            return;
//        }
//        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
//
//            // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
//            String strInfo = "在";
//            for (CityInfo cityInfo : result.getSuggestCityList()) {
//                strInfo += cityInfo.city;
//                strInfo += ",";
//            }
//            strInfo += "找到结果";
//            Toast.makeText(getActivity(), strInfo, Toast.LENGTH_LONG).show();
//        }
//
//    }
//
//    private class MyPoiOverlay extends PoiOverlay {
//
//        public MyPoiOverlay(BaiduMap baiduMap) {
//            super(baiduMap);
//        }
//
//        @Override
//        public boolean onPoiClick(int index) {
//            super.onPoiClick(index);
//            PoiInfo poi = getPoiResult().getAllPoi().get(index);
//            // if (poi.hasCaterDetails) {
//            mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
//                    .poiUid(poi.uid));
//            // }
//            return true;
//        }
//    }
//
//    /**
//     * 重写的onclick事件
//     */
//    @Override
//    public void onClick(View v) {
//        // TODO Auto-generated method stub
//        if (CommUtil.isNetConnected(getActivity())) {
//            switch (v.getId()) {
//                case R.id.btn_search_map:
//                    if (TextUtils.isEmpty(etSearchKey.getText())) {
//                        Toast.makeText(getActivity(), "请输入您要查询的地址", Toast.LENGTH_SHORT).show();
//                        break;
//                    }else{
//                        PoiSearch(etSearchKey.getText().toString());
//                    }
//
//                    break;
//                case R.id.btn_park_map:
//                    PoiSearch("市场");
//                    break;
//                case R.id.btn_hospital_map:
//                    PoiSearch("医院");
//                    break;
//                case R.id.btn_wc_map:
//                    PoiSearch("厕所");
//                    break;
//            }
//        }else {
//            Toast.makeText(getActivity(), "无网络", Toast.LENGTH_SHORT).show();
//        }
//    }
}