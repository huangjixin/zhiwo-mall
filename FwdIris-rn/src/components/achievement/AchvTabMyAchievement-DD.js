import React from 'react';
import {
    StyleSheet, BackHandler, ScrollView, View, Text, Dimensions, FlatList, DeviceEventEmitter, Image,
    TouchableWithoutFeedback, TouchableOpacity, Platform
} from 'react-native';
import {AchvDountChart} from './AchvDountChart';
import * as Progress from 'react-native-progress';
import {TabNavigator, TabBarTop, TabBarBottom} from 'react-navigation';
import SwitchSelector from 'react-native-switch-selector';
import Picker from 'react-native-picker';


class AchvTabMyAchievement_DD_Team extends React.Component {

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
    componentDidMount() {
        if (Platform.OS === 'android') {
            BackHandler.addEventListener('hardwareBackPress', () => {

            })
        }
    }
    componentWillUnmount() {
        if (Platform.OS === 'android') {
            BackHandler.removeEventListener('hardwareBackPress', () => {});
        }
    }

    componentWillMount() {
        DeviceEventEmitter.addListener('PushDataToAchvTabMyAchievement_DD', (event)=>{
            this.setState({
                data: event.movies
            });
        });
    }

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
                                }}>营业部 FYP业绩</Text>
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
                                }}>营业部 FYC业绩</Text>
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
                                }}>营业部 CASE业绩</Text>
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
                                }}>营业部 活动人力</Text>
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
                                }}>营业部主管数</Text>
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
                                }}>直接育成主管数</Text>
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
                                }}>营业部 K1续保率</Text>
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
                                }}>70%</Text>
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
                                }}>营管处在册人力</Text>
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

class AchvTabMyAchievement_DD_Person extends React.Component {
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
        screen: AchvTabMyAchievement_DD_Team,
        navigationOptions: {
            tabBarLabel: '团队',
        }
    }, 
    AchievementPerson: {
        screen: AchvTabMyAchievement_DD_Person,
        navigationOptions: {
            tabBarLabel: '个人',
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
    backBehavior:'none'
}
);
const agentGrade = 'AD';   //暂时用于显示当前职级
const agentCode = '000001';   //暂时用于显示当前职级
const VALUES_OPTIONS = ['2017年12月个人业绩', '2018年1月个人业绩', '2018年2月个人业绩', '2018年3月个人业绩', '2018年4月个人业绩', '2018年5月个人业绩', '2018年6月个人业绩', '2018年7月个人业绩', '2018年8月个人业绩'];
const LA = [{name: '郑秋冬LA',FYP:'10,000',FYC:'10,000',CASE:'10',agentCode:'00001',agentGrade:'LA'},
                    {name: '郑秋冬SM',FYP:'10,000',FYC:'10,000',CASE:'10',agentCode:'00002',agentGrade:'SM'},
                    {name: '郑秋冬SSM',FYP:'10,000',FYC:'10,000',CASE:'10',agentCode:'00001',agentGrade:'SSM'},
                    {name: '郑秋冬SD',FYP:'10,000',FYC:'10,000',CASE:'10',agentCode:'00002',agentGrade:'SD'}];

const AM = [{name: '郑秋冬AM',FYP:'10,000',FYC:'10,000',CASE:'10',agentCode:'00001',agentGrade:'AM'},
    {name: '郑秋冬SAM',FYP:'10,000',FYC:'10,000',CASE:'10',agentCode:'00002',agentGrade:'SAM'}];

const AD = [{name: '郑秋冬AD',FYP:'10,000',FYC:'10,000',CASE:'10',agentCode:'00001',agentGrade:'AD'},
    {name: '郑秋冬DD',FYP:'10,000',FYC:'10,000',CASE:'10',agentCode:'00002',agentGrade:'DD'}];
var clickMyTeamCount = 0;
export class AchvTabMyAchievement_DD extends React.Component {
    constructor(props){
        super(props);
        this.state  = {
            dataSource:AM,
            showAchvTeam:1,
            isLeader:this.isLeader(agentGrade),  //是否是领导进来
            clickMyTeamCount:0,  //我的团队进了多少层
            clickNextMyTeamArray:[{agentCode:agentCode,agentGrade:agentGrade}],//我的团队列表 点击某一项  push到数值中
            queryDateValue:[(new Date()).getUTCFullYear(),(new Date()).getUTCMonth()+1],//查询时间

        };
        // this.fetchData = this.fetchData.bind(this);
    }


    //时间选择框架
    _createDateData() {
        let date = [];
        for(let i=2018;i<2028;i++){
            let month = [];
            for(let j = 1;j<13;j++){
                let day = [];
                let _month = {};
                _month[j+''] = day;
                month.push(j);
            }
            let _date = {};
            _date[i+''] = month;
            date.push(_date);
        }
        return date;
    }
    _showDatePicker() {
        Picker.init({
            pickerData: this._createDateData(),
            pickerFontColor: [0, 0 ,0, 1],
            pickerConfirmBtnText:'确定',
            pickerCancelBtnText:'取消',
            pickerTitleText:'请选择时间',
            selectedValue:this.state.queryDateValue,
            onPickerConfirm: (pickedValue, pickedIndex) => {
                this.setState({
                    queryDateValue:pickedValue,
                })
                // alert(pickedValue[0]+'----'+pickedValue[1]);
                // console.log('date', pickedValue, pickedIndex);
            },
            // onPickerCancel: (pickedValue, pickedIndex) => {
            //     console.log('date', pickedValue, pickedIndex);
            // },
            // onPickerSelect: (pickedValue, pickedIndex) => {
            //     console.log('date', pickedValue, pickedIndex);
            // }
        });
        Picker.show();
    }





    // 网络请求
    fetchData() {
        fetch('https://facebook.github.io/react-native/movies.json', {
            })
            .then((response) => response.json())
            .then((responseData) => {
                DeviceEventEmitter.emit('PushDataToAchvTabMyAchievement_DD',responseData);
            }).done();
    }

    //我的团队切换数据
    fetchArrayData(agentCodeValue,groupTypeValue,queryDateValue){
        fetch('https://mywebsite.com/endpoint/', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                agentCode:agentCodeValue,
                groupType:groupTypeValue,
                queryDate:queryDateValue
            })
        })
            .then((response) => response.json())
            .then((responseJson) => {
                this.setState({
                    dataSource:responseJson.data,
                })
            })
            .catch((error) => {
                console.error(error);
            });
    }


    isLeader(agentGradeValue){
        if (agentGradeValue=='LA' || agentGradeValue=='SM' || agentGradeValue=='SSM' || agentGradeValue=='SD'){
            return false
        }else {
            return true
        }
    }
    componentWillMount() {
        this.fetchData();

        //当不是领导进来的时候，默认选中个人，我的团队就不用显示了
        if (this.state.isLeader==false){
            this.setState({
                showAchvTeam:0,
            })
        }
    }
    showAchvTeamFunction(value){
        this.setState({
            showAchvTeam:value,
        })
    }
    clickNextView=(value1,value2)=>{
        var isLeaderValue=this.isLeader(value2)
        clickMyTeamCount = clickMyTeamCount+1
        var clickNextMyTeamArrayValue =  JSON.stringify({
            agentCode:value1,
            agentGrade:value2,
        })
        this.state.clickNextMyTeamArray.push(clickNextMyTeamArrayValue)
        if(!isLeaderValue){
            this.setState({
                isLeader:isLeaderValue,
                showAchvTeam:0,
            })

        }
            this.setState({
                dataSource:LA,
            })
    }
    clickBackView=()=>{
        this.state.clickNextMyTeamArray.pop()
        clickMyTeamCount = clickMyTeamCount-1
        this.setState({
            dataSource:AM,
            isLeader:true,
            showAchvTeam:1,
        })
        // alert(this.state.clickNextMyTeamArray[clickMyTeamCount][agentGrade])
    }
    render() {
        return (
            <ScrollView
                style={{
                paddingVertical: 10,
                backgroundColor: "#F7F7F7",
                paddingBottom:70
            }}>
                <View style={{
                    flex: 1,
                    backgroundColor:'#F7F7F7',
                    paddingLeft:20,
                    paddingRight:20
                }}>
                    <View style={{
                        flex: 1,
                        marginTop: 25,flexDirection: 'row',
                    }}>
                        <View style={{flex: 1,justifyContent: 'flex-start',flexDirection: 'row',paddingLeft:5,alignItems:'center',}}>
                            <TouchableOpacity style={{lineHeight:40,}} onPress={this._showDatePicker.bind(this)}>
                                <Text >{this.state.queryDateValue[0]}年{this.state.queryDateValue[1]}月个人业绩</Text>
                            </TouchableOpacity>
                            <Text style={{lineHeight:40,}}>▼</Text>
                        </View>
                        <View style={{flex: 1,flexDirection: 'row',justifyContent: 'flex-end',alignItems: 'center'}}>
                            <Image style={{width:10,height:10,}} source={require('../../../img/achievement/edit.png')} />
                            <Text style={{color:'#CBCACA',marginLeft:6}} >编辑</Text>
                        </View>
                        
                    </View>
                    <View
                        style={{
                        marginTop:15,
                        backgroundColor:'#ffffff',
                        paddingLeft:10,
                        paddingRight:10,
                        borderRadius:20,
                        flex:1,
                        paddingTop:10,
                        paddingBottom:25,
                        justifyContent: 'center',
                    }}>

                        {/*//是否领导进来 true显示 false不显示*/}
                        {this.state.isLeader==true?(
                            <View style={{alignItems:'center',}}>
                                <View style={{width:200,}}>
                                    <SwitchSelector options={[
                                        { label: '个人', value: '0' },
                                        { label: '团队', value: '1' }]}
                                                    buttonColor='#ffffff'
                                                    backgroundColor='#BCBCBC'
                                                    selectedColor='#000000'
                                                    borderColor='#EAEAEA'
                                                    hasPadding = {true}
                                                    initial={1}
                                                    onPress={value => this.showAchvTeamFunction(value)} />
                                </View>
                            </View>
                        ):(null)}


                        <View style={{
                            marginTop:15,
                            paddingLeft:10,
                            paddingRight:10,
                            flex:1,
                            justifyContent: 'center'
                        }}>
                            <View style={{
                                marginTop:20,
                                flex:1,
                                justifyContent: 'center',
                                alignItems: 'center',
                            }}>
                                <View style={{
                                    flex:1,
                                    flexDirection: 'row',
                                    justifyContent: 'center',
                                    alignItems: 'center',
                                }}>
                                    <View style={{
                                        flex:1,
                                        flexDirection: 'row',
                                        justifyContent: 'flex-start',
                                        alignItems: 'center',
                                    }}>
                                        <Text style={{paddingLeft:10,paddingTop:10 ,width:40,height:40,borderRadius:20,borderColor:'#E0E0E0',color:'#E0E0E0',borderWidth:1}}>FYP</Text>
                                        <Text style={{marginLeft:10,fontSize:22,color:'#000000'}}>337,650k</Text>
                                    </View>
                                    <View style={{
                                        flex:1,
                                        flexDirection: 'row',
                                        justifyContent: 'center'
                                        }}>
                                        <View style={{
                                            flex:1,
                                            justifyContent: 'center'
                                        }}>
                                            <Text style={{fontSize:16,flex:1,textAlign:'right'}}>目标：50,000</Text>
                                            <View style={{
                                                flex:1,
                                                flexDirection: 'row',
                                                justifyContent: 'flex-end',
                                                }}>
                                                <Text style={{fontSize:16,flex:1,textAlign:'right'}}>差额：</Text>
                                                <Text style={{fontSize:16,textAlign:'right',color:'#E87722'}}>20,000</Text>
                                            </View>
                                            
                                        </View>
                                    </View>
                                    
                                </View>
                            </View>
                            
                            <Progress.Bar
                                    marginTop={20}
                                    progress={0.6}
                                    width={Dimensions.get('window').width-80}
                                    height={2}
                                    borderRadius={0}
                                    color={'#FFDD00'}
                                    backgroundColor={'#EAEAEA'}
                                    borderWidth={0}/>
                        </View>
                        
                        <View style={{
                            marginTop:15,
                            paddingLeft:10,
                            paddingRight:10,
                            flex:1,
                            justifyContent: 'center'
                        }}>
                            <View style={{
                                marginTop:20,
                                flex:1,
                                justifyContent: 'center',
                                alignItems: 'center',
                            }}>
                                <View style={{
                                    flex:1,
                                    flexDirection: 'row',
                                    justifyContent: 'center',
                                    alignItems: 'center',
                                }}>
                                    <View style={{
                                        flex:1,
                                        flexDirection: 'row',
                                        justifyContent: 'flex-start',
                                        alignItems: 'center',
                                    }}>
                                        <Text style={{paddingLeft:10,paddingTop:10 ,width:40,height:40,borderRadius:20,borderColor:'#E0E0E0',color:'#E0E0E0',borderWidth:1}}>FYC</Text>
                                        <Text style={{marginLeft:10,fontSize:22,color:'#000000'}}>337,650k</Text>
                                    </View>
                                    <View style={{
                                        flex:1,
                                        flexDirection: 'row',
                                        justifyContent: 'center'
                                        }}>
                                        <View style={{
                                            flex:1,
                                            justifyContent: 'center'
                                        }}>
                                            <Text style={{fontSize:16,flex:1,textAlign:'right'}}>目标：50,000</Text>
                                            <View style={{
                                                flex:1,
                                                flexDirection: 'row',
                                                justifyContent: 'flex-end',
                                                }}>
                                                <Text style={{fontSize:16,flex:1,textAlign:'right'}}>差额：</Text>
                                                <Text style={{fontSize:16,textAlign:'right',color:'#E87722'}}>20,000</Text>
                                            </View>
                                        </View>
                                    </View>
                                    
                                </View>
                            </View>
                            
                            <Progress.Bar
                                    marginTop={20}
                                    progress={0.6}
                                    width={Dimensions.get('window').width-80}
                                    height={2}
                                    borderRadius={0}
                                    color={'#FFDD00'}
                                    backgroundColor={'#EAEAEA'}
                                    borderWidth={0}/>
                        </View>

                        <View style={{
                            marginTop:15,
                            paddingLeft:10,
                            paddingRight:10,
                            flex:1,
                            justifyContent: 'center'
                        }}>
                            <View style={{
                                marginTop:20,
                                flex:1,
                                justifyContent: 'center',
                                alignItems: 'center',
                            }}>
                                <View style={{
                                    flex:1,
                                    flexDirection: 'row',
                                    justifyContent: 'center',
                                    alignItems: 'center',
                                }}>
                                    <View style={{
                                        flex:1,
                                        flexDirection: 'row',
                                        justifyContent: 'flex-start',
                                        alignItems: 'center',
                                    }}>
                                        <Text style={{paddingLeft:3,paddingTop:10 ,width:40,height:40,borderRadius:20,borderColor:'#E0E0E0',color:'#E0E0E0',borderWidth:1}}>CASE</Text>
                                        <Text style={{marginLeft:10,fontSize:22,color:'#000000'}}>5</Text>
                                    </View>
                                    <View style={{
                                        flex:1,
                                        flexDirection: 'row',
                                        justifyContent: 'center'
                                        }}>
                                        <View style={{
                                            flex:1,
                                            justifyContent: 'center'
                                        }}>
                                            <Text style={{fontSize:16,flex:1,textAlign:'right'}}>目标：5</Text>
                                            <View style={{
                                                flex:1,
                                                flexDirection: 'row',
                                                justifyContent: 'flex-end',
                                                }}>
                                                <Text style={{fontSize:16,flex:1,textAlign:'right'}}>差额：</Text>
                                                <Text style={{fontSize:16,textAlign:'right',color:'#E87722'}}>2</Text>
                                            </View>
                                        </View>
                                    </View>
                                    
                                </View>
                            </View>
                            
                            <Progress.Bar
                                    marginTop={20}
                                    progress={0.6}
                                    width={Dimensions.get('window').width-80}
                                    height={2}
                                    borderRadius={0}
                                    color={'#FFDD00'}
                                    backgroundColor={'#EAEAEA'}
                                    borderWidth={0}/>
                        </View>
                        {/* <AchvDountChart itemType={'FYC'} itemValue={'30,000'}/>
                        <AchvDountChart itemType={'FYP'} itemValue={'30,000'}/>
                        <AchvDountChart itemType={'CASE'} itemValue={'30,000'}/> */}
                    </View>
                </View>

                <View
                    style={{
                    flex: 1,
                    marginTop: 24,
                    paddingLeft:20,
                }}>
                    <Text style={{fontSize:16,flex:1,}}>我的业绩</Text>
                </View>
                              
                <View
                    style={{
                    flex: 1,
                    marginTop: 24,
                    paddingLeft:20,
                }}>
                    <View style={{flex:1,flexDirection: 'row'}}>
                        <View style={{flex:1,flexDirection: 'row',
                                    justifyContent: 'flex-start',
                                    alignItems: 'center',}}>
                            {/* <View style={{width:50,height:50,backgroundColor:'#ffffff',borderRadius:30}}>
                                                    
                            </View> */}
                            <Image style={{width:50,height:50}} 
                                   source={require('../../../img/achievement/achieve_icon.png')} />
                            <View style={{marginLeft:10}}>
                                <Text style={{fontSize:22,color:'#000000'}}>238,000</Text>
                                <Text style={{fontSize:14,}}>直辖组FYC业绩</Text>                  
                            </View>                    
                        </View>
                        <View style={{flex:1,flexDirection: 'row',
                                    justifyContent: 'flex-start',
                                    alignItems: 'center',}}>
                            {/* <View style={{width:50,height:50,backgroundColor:'#ffffff',borderRadius:30}}>
                                                    
                            </View> */}
                            <Image style={{width:50,height:50}} 
                                   source={require('../../../img/achievement/achieve_icon.png')} />
                            <View style={{marginLeft:10}}>
                                <Text style={{fontSize:22,color:'#000000'}}>238,000</Text>
                                <Text style={{fontSize:14,}}>直辖组活动人次</Text>                  
                            </View>                    
                        </View>
                    </View>
                    <View style={{flex:1,flexDirection: 'row',marginTop:40}}>
                        <View style={{flex:1,flexDirection: 'row',
                                    justifyContent: 'flex-start',
                                    alignItems: 'center',}}>
                            {/* <View style={{width:50,height:50,backgroundColor:'#ffffff',borderRadius:30}}>
                                                    
                            </View> */}
                            <Image style={{width:50,height:50}} 
                                   source={require('../../../img/achievement/achieve_icon.png')} />
                            <View style={{marginLeft:10}}>
                                <Text style={{fontSize:22,color:'#000000'}}>110</Text>
                                <Text style={{fontSize:14,}}>直辖组有效人力</Text>                  
                            </View>                    
                        </View>
                        <View style={{flex:1,flexDirection: 'row',
                                    justifyContent: 'flex-start',
                                    alignItems: 'center',}}>
                            {/* <View style={{width:50,height:50,backgroundColor:'#ffffff',borderRadius:30}}>
                                                    
                            </View> */}
                            <Image style={{width:50,height:50}} 
                                   source={require('../../../img/achievement/achieve_icon.png')} />
                            <View style={{marginLeft:10}}>
                                <Text style={{fontSize:22,color:'#000000'}}>2</Text>
                                <Text style={{fontSize:14,}}>直辖组育成主管</Text>                  
                            </View>                    
                        </View>
                    </View>

                    <View style={{flex:1,flexDirection: 'row',marginTop:40}}>
                        <View style={{flex:1,flexDirection: 'row',
                                    justifyContent: 'flex-start',
                                    alignItems: 'center',}}>
                            {/* <View style={{width:50,height:50,backgroundColor:'#ffffff',borderRadius:30}}>
                                                    
                            </View> */}
                            <Image style={{width:50,height:50}} 
                                   source={require('../../../img/achievement/achieve_icon.png')} />
                            <View style={{marginLeft:10}}>
                                <Text style={{fontSize:22,color:'#000000'}}>60</Text>
                                <Text style={{fontSize:14,}}>直辖组招募新人</Text>                  
                            </View>                    
                        </View>
                        <View style={{flex:1,flexDirection: 'row',
                                    justifyContent: 'flex-start',
                                    alignItems: 'center',}}>
                            {/* <View style={{width:50,height:50,backgroundColor:'#ffffff',borderRadius:30}}>
                                                    
                            </View> */}
                            <Image style={{width:50,height:50}} 
                                   source={require('../../../img/achievement/achieve_icon.png')} />
                            <View style={{marginLeft:10}}>
                                <Text style={{fontSize:22,color:'#000000'}}>20%</Text>
                                <Text style={{fontSize:14,}}>直辖组K1续保率</Text>                  
                            </View>                    
                        </View>
                    </View>
                </View>


                {/*/!*判断是否是领导进来，是的时候显示我的团队不是的时候不现实*!/*/}
                {/*{this.state.isLeader=true?(*/}
                    {/**/}
                {/*):(null)}*/}
                {/*是领导进来的时候，选择个人还是团队的时候切换是否显示我的团队，0不现实，1显示*/}
                {this.state.showAchvTeam == 0 ? (
                    null
                ) : (
                    <View>
                        <View
                            style={{
                                flex: 1,
                                marginTop: 24,
                                paddingLeft:20,
                            }}>
                            <Text style={{fontSize:16,flex:1,}}>我的团队</Text>
                        </View>


                        <View style={{
                            flex: 1,
                            marginTop: 24,
                            paddingLeft:0,
                        }}>
                            <FlatList
                                data={this.state.dataSource}
                                renderItem={({item}) =>
                                    <TouchableWithoutFeedback onPress={this.clickNextView.bind(this,item.agentCode,item.agentGrade) }>
                                    <View style={{flex:5,flexDirection: 'row',
                                        justifyContent: 'center',
                                        alignItems: 'center',borderTopWidth:1,borderTopColor:'#EBEBEB'
                                        ,paddingTop:20,paddingBottom:20,paddingLeft:20}}>
                                        <View style={{flex:1.5,flexDirection: 'column',
                                            justifyContent: 'center',
                                            alignItems: 'center',}}>
                                            <Image  source={require('../../../img/UserCenter/UserImage.jpeg')}
                                                    style={{width: 60, height: 60,borderRadius:30}} />
                                            <View style={{backgroundColor:'#ffffff',
                                                marginTop:-5,paddingBottom:2,paddingLeft:10,paddingRight:10,
                                                borderRadius:10,alignItems:'center',justifyContent:'center',flexDirection:'row'}}>
                                                <Text style={{color:'#454545',fontSize:16}}>{item.name}</Text>
                                            </View>
                                        </View>
                                        <View style={{flex:3.5,flexDirection: 'row',
                                            justifyContent: 'flex-start',
                                            alignItems: 'center',}}>
                                            <View style={{flex:1,marginTop:10,justifyContent:'center',alignItems: 'center'}}>
                                                <View style={{flex:1,flexDirection: 'row',justifyContent:'center',marginTop:10}}>

                                                    <View style={{flex:2,flexDirection: 'row',justifyContent:'center'}}>
                                                        <Text>FYP</Text>
                                                    </View>
                                                    <View style={{flex:2,flexDirection: 'row',justifyContent:'center'}}>
                                                        <Text>FYC</Text>
                                                    </View>
                                                    <View style={{flex:1,flexDirection: 'row',justifyContent:'center'}}>
                                                        <Text>CASE</Text>
                                                    </View>
                                                </View>
                                                <View style={{flex:1,flexDirection: 'row',justifyContent:'center',marginTop:5}}>
                                                    <View style={{flex:2,flexDirection: 'row',justifyContent:'center'}}>
                                                        <Text style={{color:'#454545',fontSize:20}}>{item.FYP}</Text>
                                                    </View>
                                                    <View style={{flex:2,flexDirection: 'row',justifyContent:'center'}}>
                                                        <Text style={{color:'#454545',fontSize:20}}>{item.FYC}</Text>
                                                    </View>
                                                    <View style={{flex:1,flexDirection: 'row',justifyContent:'center'}}>
                                                        <Text style={{color:'#454545',fontSize:20}}>{item.CASE}</Text>
                                                    </View>
                                                </View>
                                            </View>
                                        </View>
                                                <View style={{flex:1,flexDirection: 'row',
                                                    justifyContent: 'center',
                                                    alignItems: 'center',paddingTop:10}}>
                                                    <Image style={{width:55,height:55}}
                                                           source={require('../../../img/achievement/myTeamRightArr.png')} />
                                                    {/*<View style={{width:40,height:40,backgroundColor:'#ffffff',*/}
                                                    {/*borderRadius:20,alignItems:'center',justifyContent:'center',flexDirection:'row'}}>*/}

                                                    {/*<Text style={{color:'#FFDD00',fontSize:20}}>&gt;</Text>*/}
                                                    {/*</View>*/}
                                                </View>
                                    </View>
                                    </TouchableWithoutFeedback>
                                }
                            />


                        </View>
                    </View>
                )}
                {clickMyTeamCount==0?(null):(
                    <View style={{flexDirection:'row',marginTop:20,}}>
                        <View style={{flex:3,}}></View>
                        <TouchableWithoutFeedback onPress={this.clickBackView }>
                            <View style ={{backgroundColor:'#FFDD00',
                                borderRadius:5,flex:4,height:35,justifyContent: 'center',alignItems: 'center'}}>
                                <Text style={{fontSize:19,fontFamily:'MicrosoftYaHei'}}>返回上一级</Text>
                            </View>
                        </TouchableWithoutFeedback>
                        <View style={{flex:3,}}></View>
                    </View>




                    // <View style={{borderRadius:5,flex:1,height:35,justifyContent: 'center',alignItems: 'center'}}>
                    //     <TouchableWithoutFeedback onPress={this.clickBackView }>
                    //         <Text style={{fontSize:19,fontFamily:'MicrosoftYaHei',flex:4,backgroundColor:'#FFDD00'}}>返回上一级</Text>
                    //     </TouchableWithoutFeedback>
                    // </View>
                )}

                {/* <View
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
            <AchievementTabr></AchievementTabr> */}
            </ScrollView>

        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1
    }
});
