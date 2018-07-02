import React from 'react';
import {
    StyleSheet, BackHandler, ScrollView, View, Text, Dimensions, FlatList, DeviceEventEmitter, Image,
    TouchableWithoutFeedback, TouchableOpacity, Platform, RefreshControl, AsyncStorage
} from 'react-native';
import * as Progress from 'react-native-progress';
import SwitchSelector from 'react-native-switch-selector';
import Picker from 'react-native-picker';
import * as NumberUtils from "../../common/NumberUtils";
import * as RequestURL from "../../common/RequestURL";
import * as FetchUtils from "../../common/FetchUtils";

const g_agentGrade = 'AD';   //暂时用于显示当前职级
const g_agentCode = '000000';   //暂时用于显示当前职级
const IND_PERSONAL = 0;
const IND_TEAM = 1;

export class AchvTabMyAchievement extends React.Component {
    router = [];

    curNode = {
        personalData:{},
        teamData:{},
        agentGrade:'',
        agentCode:'',
    };

    constructor(props){
        super(props);

        this.curNode.agentCode = g_agentCode;
        this.curNode.agentGrade = g_agentGrade;

        const isLeader = this.isLeader(g_agentGrade);
        this.state  = {
            isRefreshing:false,
            isLeader:isLeader,          //是否要显示团队信息
            routerDepth:0,
            personalTeamIndicator:isLeader?IND_TEAM:IND_PERSONAL, //用作切换 个人 or 团队 数据,默认团队
            queryYear:(new Date()).getUTCFullYear(),
            queryMonth:(new Date()).getUTCMonth(),
            data:{
                agentGrade:'',
                fyc:0,
                fyp:0,
                caseNo:0,
                personalAchievement:{
                    fyc:0,
                    activeNo:0,
                    effectiveNo:0,
                    bredNo:0,
                    recruitNo:0,
                    k1Rate:0,
                },
                groupList:[]
            },
        };
    }
    // 网络请求
    fetchData() {
        const groupType = this.getGroupType(this.curNode.agentGrade)
        const personalTeamIndicator = this.state.personalTeamIndicator
        const params = {
            agentCode:this.curNode.agentCode,
            groupType:(personalTeamIndicator==IND_PERSONAL)?1:groupType,
            queryDate:this.getQueryDate()
        }
        this.setState({isRefreshing:true});
        FetchUtils.Get({
            url:RequestURL.MY_ACHIEVEMENT,
            params:params,
            success:(respData)=>{
                const data = respData.data;
                if(params.groupType==1){
                    this.curNode.personalData = data;
                }else{
                    this.curNode.teamData = data;
                }
                this.setState({
                    isRefreshing:false,
                    data:data
                });

                //set cache
                const key = 'AchvTabMyAchievement:'+g_agentCode;
                AsyncStorage.setItem(key,JSON.stringify(data));
            },
            error:(err)=>{
                this.setState({
                    isRefreshing:false,
                });
            }
        })
    }

    componentDidMount() {
        //get Cache
        const key = 'AchvTabMyAchievement:'+g_agentCode;
        AsyncStorage.getItem(key).then((result)=> {
            if (result == null) {
                return;
            }
            const cacheData = JSON.parse(result);
            this.setState({
                data:cacheData,
            });
        }).catch((error)=> {
            console.log('error:' + error.message);
        });

        this.fetchData();

        if(Platform.OS === 'android'){
            BackHandler.addEventListener('hardwareBackPress', () => {
              if(this.router.length!=0){
                  this.clickBackView();
                  return true;
              }
            })
        }
    }

    goBack = ()=>{
        console.log(this.router.length)
        if(this.router.length!=0){
            this.clickBackView();
        }
    }

    componentWillUnmount() {
        if (Platform.OS === 'android') {
            BackHandler.removeEventListener('hardwareBackPress', () => {});
        }
    }

    isEmptyData = (data)=>{
        return Object.keys(data).length==0;
    }

    switchOption = (value)=>{
        let data  = (value==IND_PERSONAL)?this.curNode.personalData:this.curNode.teamData;

        if(this.isEmptyData(data)){
            const agentGrade = this.curNode.agentGrade;
            const agentCode = this.curNode.agentCode;
            console.log('switchOption(): agentGrade:'+agentGrade + ',agentCode:'+agentCode
                + ',switch to:' + value +' (0:personal,1:team)');

            const groupType = this.getGroupType(agentGrade);
            const params = {
                agentCode:agentCode,
                groupType:(value==IND_PERSONAL)?1:groupType,
                queryDate:this.getQueryDate()
            }
            this.setState({isRefreshing:true});
            FetchUtils.Get({
                url:RequestURL.MY_ACHIEVEMENT,
                params:params,
                success:(resp)=>{
                    const respData = resp.data;
                    if(params.groupType==1){
                        this.curNode.personalData = respData;
                    }else{
                        this.curNode.teamData = respData;
                    }
                    this.setState({
                        data:respData,
                        personalTeamIndicator:value,
                        isRefreshing:false
                    });
                },
                error:()=>{
                    this.setState({isRefreshing:false});
                }
            })
        }else{
            this.setState({
                data:data,
                personalTeamIndicator:value
            });
        }


    }

    onRefresh=()=>{
        this.setState({isRefreshing:true});

        const ind = this.state.personalTeamIndicator;
        let data  = (ind==IND_PERSONAL)?this.curNode.personalData:this.curNode.teamData;
        const agentGrade = this.curNode.agentGrade;
        const agentCode = this.curNode.agentCode;
        const groupType = this.getGroupType(agentGrade);

        const params = {
            agentCode:agentCode,
            groupType:(ind==IND_PERSONAL)?1:groupType,
            queryDate:this.getQueryDate()
        }

        this.setState({isRefreshing:true});
        FetchUtils.Get({
            url:RequestURL.MY_ACHIEVEMENT,
            params:params,
            success:(resp)=>{
                const respData = resp.data;
                if(params.groupType==1){
                    this.curNode.personalData = respData;
                }else{
                    this.curNode.teamData = respData;
                }
                this.setState({
                    data:respData,
                    isRefreshing:false
                });
            },
            error:()=>{
                this.setState({isRefreshing:false});
            }
        })

    }

    isLeader = (agentGrade)=>{
        return this.getGroupType(agentGrade)!=1;
    }

    getGroupType = (agentGrade)=>{
        if (agentGrade=='LA' || agentGrade=='SM' || agentGrade=='SSM' || agentGrade=='SD'){
            return 1;
        }else if(agentGrade=='AM' || agentGrade=='SAM' ){
            return 2;
        }else if(agentGrade=='AD' || agentGrade=='DD' ){
            return 3;
        }else{
            return -1;
        }
    }


    clickNextView=(agentCode,agentGrade)=>{

        console.log('clickNextView(),current code:'+this.curNode.agentCode + ',current grade:'+ this.curNode.agentGrade
            +',next code:'+agentCode+',next grade:'+agentGrade);

        const isLeader=this.isLeader(agentGrade);
        const groupType = this.getGroupType(agentGrade)
        const params = {
            agentCode:agentCode,
            groupType:isLeader?groupType:1,
            queryDate:this.getQueryDate()
        }

        this.setState({isRefreshing:true});
        FetchUtils.Get({
            url:RequestURL.MY_ACHIEVEMENT,
            params:params,
            success:(respData)=>{
                this.setState({
                    data:respData.data,
                    isLeader:isLeader,
                    personalTeamIndicator:isLeader?IND_TEAM:IND_PERSONAL,
                    isRefreshing:false,
                    routerDepth:this.state.routerDepth+1
                });

                this.router.push(this.curNode);
                if(params.groupType==1){
                    this.curNode = {
                        personalData:respData.data,
                        teamData:{},
                        agentGrade:agentGrade,
                        agentCode:agentCode,
                    }
                }else{
                    this.curNode = {
                        personalData:{},
                        teamData:respData.data,
                        agentGrade:agentGrade,
                        agentCode:agentCode,
                    }
                }
            },
            error:()=>{
                this.setState({isRefreshing:false});
            }
        })
    }

    clickBackView=()=>{

        this.curNode = this.router.pop();
        this.setState({
            data:this.curNode.teamData,
            isLeader:true,
            personalTeamIndicator:IND_TEAM,
            routerDepth:this.state.routerDepth-1
        })

        console.log('clickBackView(),back to code:'+this.curNode.agentCode + ', grade:'+ this.curNode.agentGrade);
    }

    getQueryDate = ()=>{
        const month = this.state.queryMonth;
        const year = this.state.queryYear;
        if(month<10)
            return year + '-0' + month;
        else
            return year + '-' + month;
    }

    //时间选择 TODO
    _createDateData() {
        let date = [];
        let years = [];
        let months = [];
        const curYear = (new Date()).getUTCFullYear();
        for(let i=2000;i<=curYear;i++){
            years.push(i);
        }
        for(let j = 1;j<13;j++){
            months.push(j);
        }
        date.push(years);
        date.push(months);
        return date;
    }
    _showDatePicker() {
        Picker.init({
            pickerData: this._createDateData(),
            pickerFontColor: [0, 0 ,0, 1],
            pickerConfirmBtnText:'确定',
            pickerCancelBtnText:'取消',
            pickerTitleText:'请选择时间',
            selectedValue:[this.state.queryYear,this.state.queryMonth],
            onPickerConfirm: (pickedValue, pickedIndex) => {
                this.setState({
                    queryYear:pickedValue[0],
                    queryMonth:pickedValue[1],
                })
                this.fetchData();
            },

        });
        Picker.show();
    }

    render() {
       const {data,routerDepth} = this.state;
       const personalAchievement = data.personalAchievement;
       const groupList = data.groupList;
        return (
            <ScrollView
                style={{backgroundColor: "#F7F7F7"}}
                refreshControl={  //设置下拉刷新组件
                    <RefreshControl
                        refreshing={this.state.isRefreshing}
                        onRefresh={this.onRefresh.bind(this)}  //(()=>this.onRefresh)或者通过bind来绑定this引用来调用方法
                    />
                }
            >
                {routerDepth>0 &&(
                    <View>
                        <TouchableWithoutFeedback  onPress={this.goBack} >
                            <View style={{flex:1,paddingLeft:20,marginTop:20}}>
                                <Image style={{width:11,height:18,}}source={require('../../../img/UserCenter/GoBackBlack.png')}/>
                            </View>
                        </TouchableWithoutFeedback>
                    </View>
                )}

                <View style={{
                    flex: 1,
                    backgroundColor:'#F7F7F7',
                    paddingBottom:10,
                    paddingLeft:20,
                    paddingRight:20
                }}>
                    <View style={{
                        flex: 1,
                        marginTop: 10,flexDirection: 'row',
                    }}>
                        <View style={{flex: 1,justifyContent: 'flex-start',flexDirection: 'row',paddingLeft:5,alignItems:'center',}}>
                            <TouchableOpacity style={{lineHeight:40,}} onPress={this._showDatePicker.bind(this)}>
                                <Text >{this.state.queryYear}年{this.state.queryMonth}月个人业绩</Text>
                            </TouchableOpacity>
                            <Text style={{lineHeight:40,}}>▼</Text>
                        </View>
                        <View style={{flex: 1,flexDirection: 'row',justifyContent: 'flex-end',alignItems: 'center'}}>
                            <Image style={{width:10,height:10,}} source={require('../../../img/achievement/edit.png')} />
                            <Text style={{color:'#CBCACA',marginLeft:6}} >编辑</Text>
                        </View>
                    </View>

                    <View
                        style={[{
                            marginTop:10,
                            backgroundColor:'#ffffff',
                            paddingLeft:10,
                            paddingRight:10,
                            borderRadius:20,
                            flex:1,
                            paddingTop:10,
                            paddingBottom:25,
                            justifyContent: 'center',
                        },styles.shadowStyle]}>

                        {/*//是否领导进来 true显示 false不显示*/}
                        {this.state.isLeader==true?(
                            <View style={{alignItems:'center',}}>
                                <View style={{width:200,}}>
                                    <SwitchSelector options={[
                                        { label: '个人', value: IND_PERSONAL },
                                        { label: '团队', value: IND_TEAM }]}
                                                    buttonColor='#ffffff'
                                                    backgroundColor='#F8F8F8'
                                                    selectedColor='#000000'
                                                    borderColor='#EAEAEA'
                                                    hasPadding = {true}
                                                    initial={1}
                                                    onPress={(value) =>{this.switchOption(value)} } />
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
                                        <Text style={{textAlign:'center',paddingTop:10 ,width:40,height:40,borderRadius:20,borderColor:'#E0E0E0',color:'#E0E0E0',borderWidth:1}}>FYP</Text>
                                        <Text style={{marginLeft:10,fontSize:22,color:'#000000'}}>{NumberUtils.toLargeMoneyStr(data.fyp)}</Text>
                                    </View>
                                    <View style={{
                                        flex:1,
                                        flexDirection: 'row',
                                        justifyContent: 'flex-end',
                                    }}>
                                        <View style={{
                                            flexDirection: 'column',
                                        }}>
                                            <View style={{flex:1}}>
                                                <Text style={{fontSize:16}}>目标：50,000</Text>
                                            </View>
                                            <View style={{flex:1}}>
                                                <Text style={{fontSize:16}}>差额：
                                                    <Text style={{color:'#E87721'}}>{NumberUtils.calcBalance(50000,data.fyp)}</Text>
                                                </Text>
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
                                        <Text style={{textAlign:'center',paddingTop:10 ,width:40,height:40,borderRadius:20,borderColor:'#E0E0E0',color:'#E0E0E0',borderWidth:1}}>FYC</Text>
                                        <Text style={{marginLeft:10,fontSize:22,color:'#000000'}}>{NumberUtils.toLargeMoneyStr(data.fyc)}</Text>
                                    </View>
                                    <View style={{
                                        flex:1,
                                        flexDirection: 'row',
                                        justifyContent: 'flex-end',
                                    }}>
                                        <View style={{
                                            flexDirection: 'column',
                                        }}>
                                            <View style={{flex:1}}>
                                                <Text style={{fontSize:16}}>目标：50,000</Text>
                                            </View>
                                            <View style={{flex:1}}>
                                                <Text style={{fontSize:16}}>差额：
                                                    <Text style={{color:'#E87721'}}>{NumberUtils.calcBalance(50000,data.fyc)}</Text>
                                                </Text>
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
                                        <Text style={{textAlign:'center',paddingTop:10 ,width:40,height:40,borderRadius:20,borderColor:'#E0E0E0',color:'#E0E0E0',borderWidth:1}}>case</Text>
                                        <Text style={{marginLeft:10,fontSize:22,color:'#000000'}}>{NumberUtils.toLargeMoneyStr(data.caseNo)}</Text>
                                    </View>
                                    <View style={{
                                        flex:1,
                                        flexDirection: 'row',
                                        justifyContent: 'flex-end',
                                    }}>
                                        <View style={{
                                            flexDirection: 'column',
                                        }}>
                                            <View style={{flex:1}}>
                                                <Text style={{fontSize:16}}>目标：5</Text>
                                            </View>
                                            <View style={{flex:1}}>
                                                <Text style={{fontSize:16}}>差额：
                                                    <Text style={{color:'#E87721'}}>{NumberUtils.calcBalance(5,data.caseNo)}</Text>
                                                </Text>
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
                                <Text style={{fontSize:22,color:'#000000'}}>{NumberUtils.toLargeMoneyStr(personalAchievement.fyc)}</Text>
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
                                <Text style={{fontSize:22,color:'#000000'}}>{NumberUtils.toLargeMoneyStr(personalAchievement.activeNo)}</Text>
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
                                <Text style={{fontSize:22,color:'#000000'}}>{NumberUtils.toLargeMoneyStr(personalAchievement.effectiveNo)}</Text>
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
                                <Text style={{fontSize:22,color:'#000000'}}>{NumberUtils.toLargeMoneyStr(personalAchievement.bredNo)}</Text>
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
                                <Text style={{fontSize:22,color:'#000000'}}>{NumberUtils.toLargeMoneyStr(personalAchievement.recruitNo)}</Text>
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
                                <Text style={{fontSize:22,color:'#000000'}}>{personalAchievement.k1Rate}%</Text>
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
                {this.state.personalTeamIndicator == IND_TEAM && (
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
                            {
                                groupList.map((row,index)=>(
                                    <TouchableWithoutFeedback key={index} onPress={this.clickNextView.bind(this,row.agentCode,row.agentGrade) }>
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
                                                    <Text style={{color:'#454545',fontSize:16}}>{row.agentName}</Text>
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
                                                            <Text style={{color:'#454545',fontSize:20}}>{row.fyp}</Text>
                                                        </View>
                                                        <View style={{flex:2,flexDirection: 'row',justifyContent:'center'}}>
                                                            <Text style={{color:'#454545',fontSize:20}}>{row.fyc}</Text>
                                                        </View>
                                                        <View style={{flex:1,flexDirection: 'row',justifyContent:'center'}}>
                                                            <Text style={{color:'#454545',fontSize:20}}>{row.caseNo}</Text>
                                                        </View>
                                                    </View>
                                                </View>
                                            </View>
                                            <View style={{flex:1,flexDirection: 'row',
                                                justifyContent: 'center',
                                                alignItems: 'center',paddingTop:10}}>
                                                <Image style={{width:55,height:55}}
                                                       source={require('../../../img/achievement/myTeamRightArr.png')} />
                                            </View>
                                        </View>
                                    </TouchableWithoutFeedback>
                                ))
                            }

                        </View>
                    </View>
                )}
                <View style={{marginTop:50}}/>

            </ScrollView>

        );
    }
}

const styles = StyleSheet.create({
    shadowStyle: {
        elevation: 4,
        shadowColor: 'black',
        shadowOpacity: 0.1,
        shadowRadius: StyleSheet.hairlineWidth,
        shadowOffset: {
            height: StyleSheet.hairlineWidth,
        },
        // We don't need zIndex on Android, disable it since it's buggy
        zIndex: Platform.OS === 'android' ? 0 : 1,
    },
});
