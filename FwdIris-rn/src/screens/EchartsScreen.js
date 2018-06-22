import React from 'react';
import {
    StyleSheet,
    ScrollView,
    Button,
    Text,
    View,
    DeviceEventEmitter,
    BackHandler,
    Dimensions,
    WebView
} from 'react-native';
import Echarts from 'native-echarts';

export class EchartsScreen extends React.Component {
    constructor() {
        super();
    }

    render() {
        // const option = {     title: {         text: ''     },     tooltip: {},
        // legend: {         data: ['预算分配']     },     radar: {         shape: 'circle',
        //         name: {             textStyle: {                 color: '#000000',
        //              backgroundColor: '#999',                 borderRadius: 3,
        //          padding: [3, 5]             }         },         indicator: [
        //      {                 name: '投资',                 max: 100             }, {
        //                name: '医疗',                 max: 100             }, {
        //        name: '重疾',                 max: 100             }, {
        // name: '教育或养老',                 max: 100             }, {
        // name: '意外',                 max: 100             }, {                 name:
        // '身故',                 max: 100             }         ]     },     series: [
        //       {             name: '预算',             type: 'radar',
        // //areaStyle: {normal: {}},             data: [                 {
        //        value: [                         50,                         50,
        //                   50,                         50,                         50,
        //                         50                     ],                     name:
        // '预算分配'                 }             ]         }     ] };
        var option = {
            legend: {
                orient: 'vertical',
                x: 'left'
            },
            series: [
                {
                    name: '访问来源',
                    type: 'pie',
                    selectedMode: 'single',
                    radius: [
                        0, '39%'
                    ],
                    hoverAnimation: false,
                    label: {
                        normal: {
                            position: 'inner'
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    animation: false,
                    data: [
                        {
                            value: 100,
                            name: 6 + '%',
                            itemStyle: {
                                normal: {
                                    color: '#6a5acd'
                                }
                            },
                            label: {
                                normal: {
                                    show: true,
                                    position: 'center',
                                    textStyle: {
                                        color: '#f8f8f8',
                                        fontSize: 20
                                    }
                                }
                            }
                        }
                    ]
                }, {
                    type: 'pie',
                    radius: [
                        '40%', '43%'
                    ],
                    itemStyle: {
                        normal: {
                            color: '#6a5acd'
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    hoverAnimation: false,
                    animationEasing: 'cubicOut',
                    data: [
                        {
                            value: 6,
                            itemStyle: {
                                emphasis: {
                                    color: '#6a5acd'
                                }
                            }
                        }, {
                            value: (100 - 6),
                            itemStyle: {
                                normal: {
                                    color: '#d3d3d3'
                                }
                            }
                        }
                    ]
                }
            ]
        };
        return (
            <ScrollView style={{
                flex: 1
            }}>
                <View
                    style={{
                    flex: 1,
                    justifyContent: 'center',
                    flexDirection: 'row'
                }}>
                    <Echarts
                        option={option}
                        height={Dimensions
                        .get('window')
                        .width > Dimensions
                        .get('window')
                        .height
                        ? Dimensions
                            .get('window')
                            .height
                        : Dimensions
                            .get('window')
                            .width}
                        width={Dimensions
                        .get('window')
                        .width > Dimensions
                        .get('window')
                        .height
                        ? Dimensions
                            .get('window')
                            .height
                        : Dimensions
                            .get('window')
                            .width}/>
                </View>
                <WebView
                    width={300}
                    automaticallyAdjustContentInsets={true}
                    source={{
                    uri: 'http://www.baidu.com'
                }}
                    javaScriptEnabled={true}
                    domStorageEnabled={true}
                    decelerationRate="normal"
                    startInLoadingState={true}
                    scalesPageToFit={true}></WebView>
            </ScrollView>
        );
    }
}