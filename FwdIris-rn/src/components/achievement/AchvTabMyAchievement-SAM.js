import React from 'react';
import {StyleSheet, ScrollView, View, Text, Dimensions,FlatList,DeviceEventEmitter} from 'react-native';
import {AchvDountChart} from './AchvDountChart';
import * as Progress from 'react-native-progress';
import {TabNavigator, TabBarTop, TabBarBottom,NavigationActions} from 'react-navigation';

class AchvTabMyAchievement_SAM_Team extends React.Component {

    constructor(props){
        super(props);
        // props.dataSource = [];
        // let d = this.props.screenProps.dataSource;
        
        this.state={
            data: []
        }
        // data: [{name: '郑秋冬',FYP:'10,000',FYC:'10,000',CASE:'10'},
        //          {name: '郑秋冬',FYP:'10,000',FYC:'10,000',CASE:'10'}]
    }
    
    componentWillMount() {
        DeviceEventEmitter.addListener('PushDataToAchvTabMyAchievement_SAM', (event)=>{
            this.setState({
                data: event.movies
            });
        });
    }

    render() {
        // this.props.dataSource = this.props.screenProps.dataSource;
        return (
            <View style={styles.container}>
                    <View
                        style={{
                        flexDirection: 'column',
                        paddingTop:10
                        
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
                                }}>直辖组 FYP业绩</Text>
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
                                justifyContent: 'center',
                                backgroundColor: '#F2F2F2'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>220,220</Text>
                            </View>
                        </View>

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
                                }}>直辖组 CASE业绩</Text>
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
                                }}>35</Text>
                            </View>
                        </View>
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
                                }}>直辖组 活动人力</Text>
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
                                }}>5</Text>
                            </View>
                        </View>
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
                                justifyContent: 'center'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>18</Text>
                            </View>
                        </View>
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
                                }}>直辖组 新人活动人力</Text>
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
                                }}>7</Text>
                            </View>
                        </View>
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
                                }}>直接招募有效新人</Text>
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
                                }}>20</Text>
                            </View>
                        </View>
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
                                }}>直接育成主管数</Text>
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
                                }}>18</Text>
                            </View>
                        </View>
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
                                }}>90%</Text>
                            </View>
                        </View>
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
                                }}>直辖组在册人力</Text>
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
                                }}>6</Text>
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
                                justifyContent: 'center',
                                backgroundColor: '#F2F2F2'
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
                                justifyContent: 'center'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>2</Text>
                            </View>
                        </View>
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
                                justifyContent: 'center'
                            }}>
                                <Text
                                    style={{
                                    textAlign: 'center',
                                    color: '#666666'
                                }}>90.21%</Text>
                            </View>
                        </View>
                    </View>

                    {/* <View style={{flex:1}}>
                        
                        
                    </View> */}
                    <View style={{flexDirection: 'row',justifyContent:'center'}}>
                    <Text style={{borderRadius:20,padding:5,backgroundColor:'gray',
                        width:100,color:'#ffffff',height:30,marginTop:10}}>我的团队</Text>
                    </View>
                    
                    <FlatList
                            data={this.state.data}
                            renderItem={({item}) =>
                                <View style={{flex:1,marginTop:10,justifyContent:'center'}}>
                                    <View style={{flex:1,flexDirection: 'row',justifyContent:'center',marginTop:10}}>
                                        <View style={{flex:1,flexDirection: 'row',justifyContent:'center'}}>
                                            <Text>姓名</Text>
                                        </View>
                                        <View style={{flex:1,flexDirection: 'row',justifyContent:'center'}}>
                                            <Text>FYP</Text>
                                        </View>
                                        <View style={{flex:1,flexDirection: 'row',justifyContent:'center'}}>
                                            <Text>FYC</Text>
                                        </View>
                                        <View style={{flex:1,flexDirection: 'row',justifyContent:'center'}}>
                                            <Text>CASE</Text>
                                        </View>
                                    </View>
                                    <View style={{flex:1,flexDirection: 'row',justifyContent:'center',marginTop:5}}>
                                        <View style={{flex:1,flexDirection: 'row',justifyContent:'center'}}>
                                            <Text style={{color:'#4EBBD3'}}>{item.title}</Text>
                                        </View>
                                        <View style={{flex:1,flexDirection: 'row',justifyContent:'center'}}>
                                            <Text style={{color:'#4EBBD3'}}>{item.title}</Text>
                                        </View>
                                        <View style={{flex:1,flexDirection: 'row',justifyContent:'center'}}>
                                            <Text style={{color:'#4EBBD3'}}>{item.title}</Text>
                                        </View>
                                        <View style={{flex:1,flexDirection: 'row',justifyContent:'center'}}>
                                            <Text style={{color:'#4EBBD3'}}>{item.title}</Text>
                                        </View>
                                    </View>
                                </View>
                            }
                            />
                </View>
        );
    }
}

class AchvTabMyAchievement_SAM_Person extends React.Component {
    render() {
        return (
            <View style={styles.container}>
                    <View
                        style={{
                        flexDirection: 'column',
                        paddingTop:10
                        
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
        );
    }
}

const AchievementTabr = TabNavigator({
    AchievementTeam: {
        screen: AchvTabMyAchievement_SAM_Team,
        navigationOptions: {
            tabBarLabel: '团队'
        }
    }, 
    AchievementPerson: {
        screen: AchvTabMyAchievement_SAM_Person,
        navigationOptions: {
            tabBarLabel: '个人'
        }
    }
},{
    tabBarOptions: {
        activeTintColor: '#FFFFFF',
        inactiveTintColor: '#FF9300',
        activeBackgroundColor: '#FF9300',
        inactiveBackgroundColor: '#FFFFFF',
        labelStyle: {
            fontSize: 14, // 文字大小
            // paddingVertical:5,
            // borderBottomWidth:1,
            // borderBottomColor:'#FF9300',
            
        },
        tabStyle: {
            // backgroundColor: '#F7F7F7'
        },
        style: {
            position:'relative',
            left:(Dimensions.get('window').width-120),
            width:100,
            // paddingRight:10,
            // paddingLeft:30,
            // width:80,
            height:24,
            textAlign:'left',
            borderWidth:1,
            borderColor:'#FF9300',
            borderTopWidth:1,
            borderTopColor:'#FF9300',
            //paddingBottom:25
            //backgroundColor: '#F7F7F7', // TabBar 背景色
            //borderTopWidth:0,
        },
    },
    tabBarComponent: TabBarBottom,
    tabBarPosition: 'top',
    animationEnabled: false,
    swipeEnabled: false,
    lazy:false,
    backBehavior:'none',
    //initialRouteParams:{test:'123'}
}
);


export class AchvTabMyAchievement_SAM extends React.Component {
    constructor(props){
        super(props);
        this.state  = {
            dataSource:[{name: '郑秋冬',FYP:'10,000',FYC:'10,000',CASE:'10'},
                 {name: '郑秋冬',FYP:'10,000',FYC:'10,000',CASE:'10'}]
        };

        // this.fetchData = this.fetchData.bind(this);
    }
    
    // 网络请求
    fetchData() {
        fetch('https://facebook.github.io/react-native/movies.json', {
            })
            .then((response) => response.json())
            .then((responseData) => {
                DeviceEventEmitter.emit('PushDataToAchvTabMyAchievement_SAM',responseData);
                // this.setState({
                //     // dataSource: responseData.movies
                //     dataSource:[{name: '郑秋冬',FYP:'10,000',FYC:'10,000',CASE:'10'},
                //     {name: '郑秋冬',FYP:'10,000',FYC:'10,000',CASE:'10',
                //     {name: '郑秋冬',FYP:'10,000',FYC:'10,000',CASE:'10',
                //     {name: '郑秋冬',FYP:'10,000',FYC:'10,000',CASE:'10'}]
                // });
                // alert(JSON.stringify(this.state.data));
            }).done();
    }
    
    componentWillMount() {
        this.fetchData();
    }
    
    // 注意这个方法前面有async关键字
//   async getMoviesFromApi() {
//     try {
//       // 注意这里的await语句，其所在的函数必须有async关键字声明
//       let response = await fetch('https://facebook.github.io/react-native/movies.json');
//       let responseJson = await response.json();
//       return responseJson.movies;
//     } catch(error) {
//       console.error(error);
//     }
//   }

    

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
                        <Text >2018年3月个人业绩 ▼</Text>
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

            <View style={{marginTop: 10,flex:1,flexDirection: 'row'}}>
                <Text>| 我的业绩</Text>                     
            </View>
            <AchievementTabr></AchievementTabr>
            </ScrollView>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1
    }
});


