import React from 'react';
import {StyleSheet, ScrollView, View, Text, Dimensions} from 'react-native';
import {AchvDountChart} from './AchvDountChart';
import * as Progress from 'react-native-progress';

export class AchvTabMyAchievement_SM extends React.Component {
    render() {
        return (
            <ScrollView
                style={{
                paddingVertical: 10,
                backgroundColor: "#F7F7F7",
                paddingLeft: 10,
                paddingRight: 10,
                paddingBottom:70
            }}>
                <View style={{
                    flex: 1
                }}>
                    <View style={{
                        marginTop: 10
                    }}>
                        <Text >2018年1月个人业绩 ▼</Text>
                    </View>
                    <View
                        style={{
                        flexDirection: 'row'
                    }}>
                        <AchvDountChart itemType={'FYC'} itemValue={'30,000'}/>
                        <AchvDountChart itemType={'FYP'} itemValue={'30,000'}/>
                        <AchvDountChart itemType={'CASE'} itemValue={'30,000'}/>
                    </View>
                </View>
                <View
                    style={{
                    flex: 1,
                    marginTop: 10
                }}>
                    <Text>| 活动量管理</Text>
                    <View style={{
                        flex: 1
                    }}>
                        <View
                            style={{
                            flex: 1,
                            flexDirection: 'row',
                            marginTop: 10
                        }}>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'flex-start'
                            }}>
                                <Text>制作建议书</Text>
                            </View>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'flex-end',
                                flexDirection: 'row'
                            }}>
                                <Text
                                    style={{
                                    color: '#FF9900'
                                }}>8</Text>
                                <Text>/10</Text>
                            </View>

                        </View>
                        <Progress.Bar
                            progress={0.3}
                            width={Dimensions
                            .get('window')
                            .width-20}
                            height={8}
                            borderRadius={0}
                            color={'#FF9900'}/>
                    </View>
                    <View style={{
                        flex: 1
                    }}>
                        <View
                            style={{
                            flex: 1,
                            flexDirection: 'row',
                            marginTop: 5
                        }}>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'flex-start'
                            }}>
                                <Text>新增客户</Text>
                            </View>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'flex-end',
                                flexDirection: 'row'
                            }}>
                                <Text
                                    style={{
                                    color: '#FF9900'
                                }}>20</Text>
                                <Text>/20</Text>
                            </View>

                        </View>
                        <Progress.Bar
                            progress={1}
                            width={Dimensions
                            .get('window')
                            .width-20}
                            height={8}
                            borderRadius={0}
                            color={'#FF9900'}/>
                    </View>
                    <View style={{
                        flex: 1
                    }}>
                        <View
                            style={{
                            flex: 1,
                            flexDirection: 'row',
                            marginTop: 5
                        }}>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'flex-start'
                            }}>
                                <Text>完成保单</Text>
                            </View>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'flex-end',
                                flexDirection: 'row'
                            }}>
                                <Text
                                    style={{
                                    color: '#FF9900'
                                }}>4</Text>
                                <Text>/6</Text>
                            </View>

                        </View>
                        <Progress.Bar
                            progress={0.6}
                            width={Dimensions
                            .get('window')
                            .width-20}
                            height={8}
                            borderRadius={0}
                            color={'#FF9900'}/>
                    </View>
                    <View style={{
                        flex: 1
                    }}>
                        <View
                            style={{
                            flex: 1,
                            flexDirection: 'row',
                            marginTop: 5
                        }}>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'flex-start'
                            }}>
                                <Text>拜访客户</Text>
                            </View>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'flex-end',
                                flexDirection: 'row'
                            }}>
                                <Text
                                    style={{
                                    color: '#FF9900'
                                }}>20</Text>
                                <Text>/6</Text>
                            </View>

                        </View>
                        <Progress.Bar
                            progress={0.6}
                            width={Dimensions
                            .get('window')
                            .width-20}
                            height={8}
                            borderRadius={0}
                            color={'#FF9900'}/>
                    </View>
                </View>

                <View style={styles.container}>
                    <View style={{
                        marginTop: 10
                    }}>
                        <Text>| 我的业绩</Text>
                    </View>
                    <View
                        style={{
                        flexDirection: 'column',
                        marginLeft: 10,
                        marginRight: 10,
                        height: 180
                    }}>
                        {/* Row Header */}
                        <View
                            style={{
                            backgroundColor: '#999999',
                            flexDirection: 'row',
                            height: 30,
                            justifyContent: 'center'
                        }}>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: 'white'
                                }}>关键指标</Text>
                            </View>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: 'white'
                                }}>实际完成</Text>
                            </View>
                        </View>

                        {/* Row */}
                        <View
                            style={{
                            flexDirection: 'row',
                            height: 30,
                            justifyContent: 'center'
                        }}>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center',
                                backgroundColor: '#CCCCCC'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>直辖组 FYC业绩</Text>
                            </View>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>220,220</Text>
                            </View>
                        </View>

                        {/* Row */}
                        <View
                            style={{
                            flexDirection: 'row',
                            height: 30,
                            justifyContent: 'center'
                        }}>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center',
                                backgroundColor: '#CCCCCC'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>直辖组活动人次</Text>
                            </View>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center',
                                backgroundColor: '#F2F2F2'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>18</Text>
                            </View>
                        </View>

                        {/* Row */}
                        <View
                            style={{
                            flexDirection: 'row',
                            height: 30,
                            justifyContent: 'center'
                        }}>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center',
                                backgroundColor: '#CCCCCC'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>直辖有效人力</Text>
                            </View>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>5</Text>
                            </View>
                        </View>

                        {/* Row */}
                        <View
                            style={{
                            flexDirection: 'row',
                            height: 30,
                            justifyContent: 'center'
                        }}>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center',
                                backgroundColor: '#CCCCCC'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>直接育成主管</Text>
                            </View>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center',
                                backgroundColor: '#F2F2F2'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>2</Text>
                            </View>
                        </View>

                        {/* Row */}
                        <View
                            style={{
                            flexDirection: 'row',
                            height: 30,
                            justifyContent: 'center'
                        }}>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center',
                                backgroundColor: '#CCCCCC'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>招募有效新人</Text>
                            </View>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>2</Text>
                            </View>
                        </View>

                        {/* Row */}
                        <View
                            style={{
                            flexDirection: 'row',
                            height: 30,
                            justifyContent: 'center'
                        }}>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center',
                                backgroundColor: '#CCCCCC'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>直辖人K1续保率</Text>
                            </View>
                            <View
                                style={{
                                flex: 1,
                                justifyContent: 'center',
                                backgroundColor: '#F2F2F2'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>90.21%</Text>
                            </View>
                        </View>

                    </View>
                </View>
            </ScrollView>

        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1
    }
});
