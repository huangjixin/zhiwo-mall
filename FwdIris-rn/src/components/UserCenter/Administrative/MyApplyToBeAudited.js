import React from 'react';
import {StyleSheet,ScrollView,Text,View,Image,Dimensions,TouchableOpacity,FlatList,TouchableWithoutFeedback,DeviceEventEmitter,Modal,Button,ActivityIndicator,} from 'react-native';
import AdministrativeList from '../Administrative.json';
import * as RequestURL from "../../../common/RequestURL";
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
var halfWidth = ScreenWidth/2;

const quit = require('../../../../img/UserCenter/Quit.png'); //0离职
const leave = require('../../../../img/UserCenter/leave.png');//1请假
const promotion = require('../../../../img/UserCenter/Promotion.png');//2晋升
const complex = require('../../../../img/UserCenter/Complex.png');//3复效
const address = require('../../../../img/UserCenter/address.gif');//4 地址
const phone = require('../../../../img/UserCenter/phone.gif');//5手机号
const bank = require('../../../../img/UserCenter/Bank.gif');//6银行卡
const wages = require('../../../../img/UserCenter/wages.png');//7收入证明
const job = require('../../../../img/UserCenter/job.png');//8工作证明
const other = require('../../../../img/UserCenter/other.png');//9其它收入证明
let pageNo = 1;//当前第几页
let totalPage=10;//总的页数
let itemNo=10;//item的个数
var myApplyToBeAuditedListCache = [];
var modelItemCache = new Set([]);
export class MyApplyToBeAudited extends React.Component {
    constructor(props){
        var AdministrativeArr = AdministrativeList;
        super(props);
        this.state = {
            // modelItem:[11],
            modelItem:modelItemCache.size!=0?modelItemCache:new Set([11]),
            isFilterCondition:false,  //筛选model控制
            showFoot:0, // 控制foot， 0：隐藏footer  1：已加载完成,没有更多数据   2 ：显示加载中

            refreshing: false,//当前的刷新状态
            myApplyToBeAuditedAllLoaded:false,
            myApplyToBeAuditedStart:0,
            myApplyToBeAuditedSize:10,
            myApplyToBeAuditedList:[],
        }
    }
    componentWillMount() {
        if(myApplyToBeAuditedListCache.length==0){
            this.fetchData(1,false);
        }else {
            this.setState({
                myApplyToBeAuditedList:myApplyToBeAuditedListCache
            });
        }
    }
    componentWillUnmount(){
        myApplyToBeAuditedListCache = this.state.myApplyToBeAuditedList;
        modelItemCache=this.state.modelItem;
    }
    // 网络请求
    // page 请求第几页    types 筛选类型   isToLoad  是否上拉加载数据
    fetchData(page,isToLoad) {
        if(isToLoad == true){

        }else{
            this.setState({
                myApplyToBeAuditedList:[],
                // myApplyToBeCheckAllLoaded:false,
                // showFoot:0,
            });
        }
        var typeStr = Array.from(this.state.modelItem).toString();
        var status = -1;
        var agentCode='10000';
        var size = 4;
        var url;
        if(typeStr==='11'|| typeStr===''){
            url = RequestURL.HOST+'applyForm/selectOAApplyFormVo?status='+status+'&agentCode='+agentCode+'&page='+page+'&size='+size;
        }else {
            url = RequestURL.HOST+'applyForm/selectOAApplyFormVo?status='+status+'&agentCode='+agentCode+'&page='+page+'&size='+size+'&types='+typeStr;
        }
        fetch(url, {
        })
            .then((response) => response.json())
            .then((responseData) => {

                if (isToLoad){
                    let oldData = this.state.myApplyToBeAuditedList.concat(responseData);
                    this.setState({
                        myApplyToBeAuditedList:oldData,
                        showFoot:0,
                    });
                }else {
                    pageNo=1;
                    this.setState({
                        myApplyToBeAuditedList:responseData,
                        myApplyToBeAuditedAllLoaded:false,
                        showFoot:0,
                    });
                }
                if(responseData.length<size){
                    this.setState({
                        myApplyToBeAuditedAllLoaded:true,
                        showFoot:1,
                    });
                }
                // console.log(responseData);
                // 真正做的时候改。
                // let data = this.state.myApplyToBeCheckList.concat(responseData.movies);

                // this.setState( {
                //     myApplyToBeCheckList:data,
                //     selectedIndex:0,
                // })
            }).done();
    }
//选中的item跳转到详情页
    administrativeToOaApplyFormDetail=(value)=>{
        DeviceEventEmitter.emit('AdministrativeToOaApplyFormDetail',{value}); //oa申请跳转到详情页
    }
    //为了处理item头部信息
    judgeData(value){
        var titleImage;
        var titleText;
        switch(value){
            case '0':titleText='离职申请';titleImage= quit;break;
            case '1':titleText='请假申请';titleImage= leave;break;
            case '2':titleText='晋升申请';titleImage= promotion;break;
            case '3':titleText='复效申请';titleImage= complex;break;
            case '4':titleText='地址申请';titleImage= address;break;
            case '5':titleText='手机号申请';titleImage= phone;break;
            case '6':titleText='银行卡申请';titleImage= bank;break;
            case '7':titleText='收入证明申请';titleImage= wages;break;
            case '8':titleText='工作证明申请';titleImage= job;break;
            case '9':titleText='其它证明申请';titleImage= other;break;
            default:
        }
        return {titleImage,titleText};
    }
    //时间转换
    timeTwistsIntoDate=(timeTwists)=>{
        let standardTime = new Date(timeTwists);
        let year = standardTime.getFullYear();
        let month = standardTime.getMonth()+1;
        let date = standardTime.getDate();

        return year+'/'+month+'/'+date;
    }
    //时间转换(具体到上下午)
    timeTwistsIntoAmToPm=(timeTwists)=>{
        let standardTime = new Date(timeTwists);
        let year = standardTime.getFullYear();
        let month = standardTime.getMonth()+1;
        let date = standardTime.getDate();
        let hours = standardTime.getHours();
        let AmToPm='';
        if(0<=hours<12){
            AmToPm='上午';
        }else {
            AmToPm='下午';
        }

        return year+'/'+month+'/'+date+' '+AmToPm;
    }
    //点击筛选 model展示
    filterCondition=()=>{
        //alert('筛选');
        this.setState({
            isFilterCondition:true
        })

    }

    onRequestClose() {
        this.setState({
            isFilterCondition:false
        });
    }
    //model 类型按钮点击调用方法
    clickModelItem=(index)=>{
        if (index===11){
            this.state.modelItem.clear();
            this.state.modelItem.add(index);
        }else if(this.state.modelItem.has(11)===true&&index!==11){
            this.state.modelItem.delete(11);
            this.state.modelItem.add(index);
        }else {
            if (this.state.modelItem.has(index)===true){
                this.state.modelItem.delete(index);
            }else {
                this.state.modelItem.add(index);
            }
        }
        this.setState({
            modelItem:this.state.modelItem,
        })
    }
    //model 中点击确定调用方法
    clickModel=()=>{
        this.setState({
            isFilterCondition:false
        });

        this.fetchData(1,false);
    }
    //点击空白处隐藏modal
    hiddenModal=()=>{
        this.setState({
            isFilterCondition:false
        });
    }
    render() {
        return (
            <View style={{height:ScreenHeight-140,}}>
                <View style={{marginLeft:40,fontSize:16,marginTop:15,paddingBottom:10,}}>
                    <TouchableOpacity style={{width:60,}} onPress={this.filterCondition}>
                        <Text style={{fontSize:16,}}>筛选▼</Text>
                    </TouchableOpacity>
                </View>

                <FlatList
                    style={{}}
                    initialNumToRender ={10}
                    data={this.state.myApplyToBeAuditedList}

                    //添加头尾布局
                    // ListHeaderComponent={this.header}

                    //下拉刷新，必须设置refreshing状态
                    onRefresh={this.onRefresh}
                    refreshing={this.state.refreshing}

                    //上拉加载数据
                    ListFooterComponent={this._renderFooter.bind(this)}
                    onEndReached={this._onEndReached.bind(this)}
                    onEndReachedThreshold={0.1}

                    renderItem={({item}) =>
                        //{/* 循环数组，根据Type去把信息给显示出来 */}
                        <TouchableWithoutFeedback onPress={this.administrativeToOaApplyFormDetail.bind(this,{itemData:item,baseStatus:0,})}>
                            <View>
                                {/*//4 地址 5手机号 6银行卡*/}
                                {item.type==4||item.type==5||item.type==6 ?(
                                    <View style={{marginTop:15,backgroundColor:'#FFFFFF',marginLeft:20,marginRight:20,borderRadius:10,paddingBottom:20,paddingTop:10,}}>
                                        <View style={{alignItems:'center',flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                            <Image style={{width:50, height:50,marginRight:10,}} source={this.judgeData(item.type).titleImage} />
                                            <Text style={{fontSize:18,flex:7,color:'#0D0D0D',}}>{this.judgeData(item.type).titleText}</Text>
                                            <Text style={{fontSize:18,flex:3,textAlign:'right',color:item.status=="0"?'#EC8C44':item.status=="1"?"#0F6B72":"#DC4254",fontWeight:'bold'}}>{item.status=="0"?'待审批':
                                                (item.status=="1"?'审批通过':(item.status=='2'?'不通过':'待签约'))}</Text>
                                        </View>
                                    </View>
                                ):(
                                    //0离职 1请假 2晋升 3复效 7收入证明 8工作证明 9其它收入证明
                                    <View style={{marginTop:15,backgroundColor:'#FFFFFF',marginLeft:20,marginRight:20,borderRadius:10,paddingBottom:20,paddingTop:10,}}>
                                        <View style={{alignItems:'center',flexDirection: 'row',marginLeft:15,marginRight:15, borderBottomWidth:0.5,borderBottomColor:'#CCCCCC',paddingBottom:10,}}>
                                            <Image style={{width:35, height:35,marginRight:10,}} source={this.judgeData(item.type).titleImage} />
                                            <Text style={{fontSize:18,flex:7,color:'#0D0D0D',}}>{this.judgeData(item.type).titleText}</Text>
                                            <Text style={{fontSize:18,flex:3,textAlign:'right',color:item.status=="0"?'#EC8C44':item.status=="1"?"#0F6B72":"#DC4254",fontWeight:'bold'}}>{item.status=="0"?'待审批':
                                                (item.status=="1"?'审批通过':(item.status=='2'?'不通过':'待签约'))}</Text>
                                        </View>
                                        {/*//0离职*/}
                                        {item.type==0?(
                                            <View>
                                                <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                    <Text style={{fontSize:15,flex:7,}}>离职原因：{item.title}</Text>
                                                    <Text style={{fontSize:15,flex:3,textAlign:'right',}}>{this.timeTwistsIntoDate(item.createDatetime)}</Text>
                                                </View>
                                                <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                    <Text style={{fontSize:15,flex:7,}}>详细说明：{item.description}</Text>
                                                    <Text style={{fontSize:15,flex:3,textAlign:'right',}}>{item.agentName}</Text>
                                                </View>
                                            </View>
                                        ):(// 1请假
                                            item.type==1?(
                                                <View>
                                                    <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                        <Text style={{fontSize:15,flex:7,}}>请假类型：{item.type==0?'病假':
                                                            (item.type==1?'婚假':
                                                                (item.type==2?'产假':
                                                                    (item.type==3?'陪产假':'丧假')))}</Text>
                                                        <Text style={{fontSize:15,flex:3,textAlign:'right',}}>{item.createDatetime}</Text>
                                                    </View>
                                                    <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                        <Text style={{fontSize:15,flex:7,}}>开始时间：{this.timeTwistsIntoAmToPm(item.startTime)}</Text>
                                                    </View>
                                                    <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                        <Text style={{fontSize:15,flex:7,}}>结束时间：{this.timeTwistsIntoAmToPm(item.endTime)}</Text>
                                                        <Text style={{fontSize:15,flex:3,textAlign:'right',}}>{item.agentName}</Text>
                                                    </View>
                                                </View>
                                            ):(//2晋升
                                                item.type==2?(
                                                    <View>
                                                        <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                            <Text style={{fontSize:15,flex:7,}}>当前职级：{item.currentGrade}</Text>
                                                            <Text style={{fontSize:15,flex:3,textAlign:'right',}}>{this.timeTwistsIntoDate(item.createDatetime)}</Text>
                                                        </View>
                                                        <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                            <Text style={{fontSize:15,flex:7,}}>晋级职级：{item.upGrade}</Text>
                                                            <Text style={{fontSize:15,flex:3,textAlign:'right',}}>{item.agentName}</Text>
                                                        </View>
                                                    </View>):(
                                                    // 3复效
                                                    item.type==3?(
                                                        <View>
                                                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                                <Text style={{fontSize:15,flex:7,}}>复效人员：{item.name}</Text>
                                                                <Text style={{fontSize:15,flex:3,textAlign:'right',}}>{this.timeTwistsIntoDate(item.createDatetime)}</Text>
                                                            </View>
                                                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                                <Text style={{fontSize:15,flex:7,}}>详细说明：{item.description}</Text>
                                                                <Text style={{fontSize:15,flex:3,textAlign:'right',}}>{item.agentName}</Text>
                                                            </View>
                                                        </View>

                                                    ):(item.type==7?(// 7收入证明 imcomeproveMonth
                                                        <View>
                                                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                                <Text style={{fontSize:15,flex:7,}}>时间：{item.imcomeproveMonth==0?(3):(item.imcomeproveMonth==1?(6):(item.imcomeproveMonth==2?(9):(12)))}个月</Text>
                                                                <Text style={{fontSize:15,flex:3,textAlign:'right',}}>{this.timeTwistsIntoDate(item.createDatetime)}</Text>
                                                            </View>
                                                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                                <Text style={{fontSize:15,flex:7,}}>用途：{item.description}</Text>
                                                            </View>
                                                        </View>
                                                    ):(item.type==8?(//8工作证明
                                                        <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                            <Text style={{fontSize:15,flex:7,}}>用途：{item.description}</Text>
                                                            <Text style={{fontSize:15,flex:3,textAlign:'right',}}>{this.timeTwistsIntoDate(item.createDatetime)}</Text>
                                                        </View>
                                                    ):(
                                                        //9其它收入证明
                                                        <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                                            <Text style={{fontSize:15,flex:7,}}>用途：{item.description}</Text>
                                                            <Text style={{fontSize:15,flex:3,textAlign:'right',}}>{this.timeTwistsIntoDate(item.createDatetime)}</Text>
                                                        </View>
                                                    ))))))}
                                    </View>
                                )}
                            </View>
                        </TouchableWithoutFeedback>



                    }
                />
                {/*
                        <TouchableWithoutFeedback onPress={this.administrativeToOaApplyFormDetail.bind(this,{type:2,status:1,baseStatus:0,})}>
                        <View style={{marginTop:15,backgroundColor:'#FFFFFF',marginLeft:20,marginRight:20,borderRadius:10,paddingBottom:20,paddingTop:10,}}>
                            <View style={{alignItems:'center',paddingBottom:10,flexDirection: 'row',borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:15,marginRight:15,}}>
                                <Image style={{width:35, height:35,marginRight:10,}} source={require('../../../img/UserCenter/Promotion.png')} />
                                <Text style={{fontSize:18,flex:7,color:'#0D0D0D',}}>晋级申请</Text>
                                <Text style={{fontSize:18,flex:3,textAlign:'right',color:'#24787E',fontWeight:'bold'}}>变更成功</Text>
                            </View>
                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,flex:7,}}>当前职级：LA</Text>
                                <Text style={{fontSize:15,flex:3,textAlign:'right',}}>2018/01/01</Text>
                            </View>
                            <View style={{flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,}}>晋升职级：SM</Text>
                            </View>
                        </View>
                        </TouchableWithoutFeedback>

                        <TouchableWithoutFeedback onPress={this.administrativeToOaApplyFormDetail.bind(this,{type:3,status:1,baseStatus:0,})}>
                        <View style={{marginTop:15,backgroundColor:'#FFFFFF',marginLeft:20,marginRight:20,borderRadius:10,paddingBottom:20,paddingTop:10,}}>
                            <View style={{alignItems:'center',paddingBottom:10,flexDirection: 'row',borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:15,marginRight:15,}}>
                                <Image style={{width:35, height:35,marginRight:10,}} source={require('../../../img/UserCenter/Complex.png')} />
                                <Text style={{fontSize:18,flex:7,color:'#0D0D0D',}}>复效申请</Text>
                                <Text style={{fontSize:18,flex:3,textAlign:'right',color:'#24787E',fontWeight:'bold'}}>变更成功</Text>
                            </View>
                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,flex:7,}}>复效人员：林心如</Text>
                                <Text style={{fontSize:15,flex:3,textAlign:'right',}}>2018/01/01</Text>
                            </View>
                            <View style={{flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,}}>详细说明：之前的表现一直不错，感觉后期可以重用，所以之前的表现一直不错</Text>
                            </View>
                        </View>
                        </TouchableWithoutFeedback>

                        <View style={{marginTop:15,backgroundColor:'#FFFFFF',marginLeft:20,marginRight:20,borderRadius:10,paddingBottom:20,paddingTop:10,}}>
                            <View style={{alignItems:'center',paddingBottom:10,flexDirection: 'row',borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:15,marginRight:15,}}>
                                <Image style={{width:35, height:35,marginRight:10,}} source={require('../../../img/UserCenter/wages.png')} />
                                <Text style={{fontSize:18,flex:7,color:'#0D0D0D',}}>收入证明</Text>
                                <Text style={{fontSize:18,flex:3,textAlign:'right',color:'#D1071F',fontWeight:'bold'}}>不通过</Text>
                            </View>
                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,flex:7,}}>时间：12个月</Text>
                                <Text style={{fontSize:15,flex:3,textAlign:'right',}}>2018/01/01</Text>
                            </View>
                            <View style={{flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,}}>用途：中国银行房屋贷款</Text>
                            </View>
                        </View>

                        <View style={{marginTop:15,backgroundColor:'#FFFFFF',marginLeft:20,marginRight:20,borderRadius:10,paddingBottom:20,paddingTop:10,}}>
                            <View style={{alignItems:'center',paddingBottom:10,flexDirection: 'row',borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:15,marginRight:15,}}>
                                <Image style={{width: 35, height: 35,marginRight:10,}} source={require('../../../img/UserCenter/wages.png')} />
                                <Text style={{fontSize:18,flex:7,color:'#0D0D0D',}}>工作证明</Text>
                                <Text style={{fontSize:18,flex:3,textAlign:'right',color:'#9E9E9E',fontWeight:'bold'}}>已撤销</Text>
                            </View>
                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,flex:7,}}>用途：中国银行房屋贷款</Text>
                                <Text style={{fontSize:15,flex:3,textAlign:'right',}}>2018/01/01</Text>
                            </View>
                        </View>*/}


                {/*
                        <TouchableWithoutFeedback onPress={this.administrativeToOaApplyFormDetail.bind(this,{type:1,status:1,baseStatus:0,})}>
                            <View style={{marginTop:15,backgroundColor:'#FFFFFF',marginLeft:20,marginRight:20,borderRadius:10,paddingBottom:20,paddingTop:10,}}>
                                <View style={{alignItems:'center',paddingBottom:10,flexDirection: 'row',borderBottomWidth:0.5,borderBottomColor:'#CCCCCC',marginLeft:15,marginRight:15,}}>
                                    <Image style={{width:35, height:35,marginRight:10,}} source={require('../../../img/UserCenter/leave.png')} />
                                    <Text style={{fontSize:18,flex:7,color:'#0D0D0D',}}>请假申请</Text>
                                    <Text style={{fontSize:18,flex:3,textAlign:'right',color:'#EC8C44',fontWeight:'bold'}}>待审批</Text>
                                </View>
                                <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                    <Text style={{fontSize:15,flex:7,}}>请假类型：年假</Text>
                                    <Text style={{fontSize:15,flex:3,textAlign:'right',}}>06:30</Text>
                                </View>
                                <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                    <Text style={{fontSize:15,flex:7,}}>开始时间：2018-05-18 上午</Text>
                                </View>
                                <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                    <Text style={{fontSize:15,flex:7,}}>结束时间：2018-05-18下午</Text>
                                    <Text style={{fontSize:15,flex:3,textAlign:'right',}}>黄晓明</Text>
                                </View>
                            </View>
                        </TouchableWithoutFeedback>

                        <TouchableWithoutFeedback onPress={this.administrativeToOaApplyFormDetail.bind(this,{type:2,status:0,baseStatus:0,})}>
                        <View style={{marginTop:15,backgroundColor:'#FFFFFF',marginLeft:20,marginRight:20,borderRadius:10,paddingBottom:20,paddingTop:10,}}>
                            <View style={{alignItems:'center',paddingBottom:10,flexDirection: 'row',borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:15,marginRight:15,}}>
                                <Image style={{width:35, height:35,marginRight:10,}} source={require('../../../img/UserCenter/Promotion.png')} />
                                <Text style={{fontSize:18,flex:7,color:'#0D0D0D',}}>晋级申请</Text>
                                <Text style={{fontSize:18,flex:3,textAlign:'right',color:'#EC8C44',fontWeight:'bold'}}>待审批</Text>
                            </View>
                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,flex:7,}}>当前职级：LA</Text>
                                <Text style={{fontSize:15,flex:3,textAlign:'right',}}>2018/01/01</Text>
                            </View>
                            <View style={{flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,}}>晋升职级：SM</Text>
                            </View>
                        </View>
                        </TouchableWithoutFeedback>


                        <TouchableWithoutFeedback onPress={this.administrativeToOaApplyFormDetail.bind(this,{type:3,status:0,baseStatus:0,})}>
                        <View style={{marginTop:15,backgroundColor:'#FFFFFF',marginLeft:20,marginRight:20,borderRadius:10,paddingBottom:20,paddingTop:10,}}>
                            <View style={{alignItems:'center',paddingBottom:10,flexDirection: 'row',borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:15,marginRight:15,}}>
                                <Image style={{width:35, height:35,marginRight:10,}} source={require('../../../img/UserCenter/Complex.png')} />
                                <Text style={{fontSize:18,flex:7,color:'#0D0D0D',}}>复效申请</Text>
                                <Text style={{fontSize:18,flex:3,textAlign:'right',color:'#EC8C44',fontWeight:'bold'}}>待审批</Text>
                            </View>
                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,flex:7,}}>复效人员：林心如</Text>
                                <Text style={{fontSize:15,flex:3,textAlign:'right',}}>2018/01/01</Text>
                            </View>
                            <View style={{flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,}}>详细说明：之前的表现一直不错，感觉后期可以重用，所以之前的表现一直不错</Text>
                            </View>
                        </View>
                        </TouchableWithoutFeedback>

                        <View style={{marginTop:15,backgroundColor:'#FFFFFF',marginLeft:20,marginRight:20,borderRadius:10,paddingBottom:20,paddingTop:10,}}>
                            <View style={{alignItems:'center',paddingBottom:10,flexDirection: 'row',borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:15,marginRight:15,}}>
                                <Image style={{width:35, height:35,marginRight:10,}} source={require('../../../img/UserCenter/wages.png')} />
                                <Text style={{fontSize:18,flex:7,color:'#0D0D0D',}}>收入证明</Text>
                                <Text style={{fontSize:18,flex:3,textAlign:'right',color:'#EC8C44',fontWeight:'bold'}}>待审批</Text>
                            </View>
                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,flex:7,}}>时间：12个月</Text>
                                <Text style={{fontSize:15,flex:3,textAlign:'right',}}>2018/01/01</Text>
                            </View>
                            <View style={{flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,}}>用途：中国银行房屋贷款</Text>
                            </View>
                        </View>

                        <View style={{marginTop:15,backgroundColor:'#FFFFFF',marginLeft:20,marginRight:20,borderRadius:10,paddingBottom:20,paddingTop:10,}}>
                            <View style={{alignItems:'center',paddingBottom:10,flexDirection: 'row',borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:15,marginRight:15,}}>
                                <Image style={{width: 35, height: 35,marginRight:10,}} source={require('../../../img/UserCenter/wages.png')} />
                                <Text style={{fontSize:18,flex:7,color:'#0D0D0D',}}>工作证明</Text>
                                <Text style={{fontSize:18,flex:3,textAlign:'right',color:'#EC8C44',fontWeight:'bold'}}>待审批</Text>
                            </View>
                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:15,marginRight:15,}}>
                                <Text style={{fontSize:15,flex:7,}}>用途：中国银行房屋贷款</Text>
                                <Text style={{fontSize:15,flex:3,textAlign:'right',}}>2018/01/01</Text>
                            </View>
                        </View>*/}
                <View style={{}}>
                    <Modal
                        animationType='slide'           // 从底部滑入
                        transparent={true}             // 不透明
                        visible={this.state.isFilterCondition}    // 根据isModal决定是否显示
                        onRequestClose={() => {this.onRequestClose()}}  // android必须实现
                    >

                        <TouchableWithoutFeedback style={{}} onPress={this.hiddenModal}>
                            <View style={{height:ScreenHeight-240,backgroundColor:'rgba(0, 0, 0, 0.3)'}}></View>
                        </TouchableWithoutFeedback>
                        <View style={{flex:1,justifyContent:'flex-end',alignItems:'flex-end',backgroundColor:'rgba(0, 0, 0, 0.3)'}}>
                            <View style={{height:220,width:ScreenWidth,backgroundColor:'white'}}>

                                <Text style={{fontWeight:'bold',color:'#151515',fontSize:16,paddingTop:20,paddingLeft:15,paddingBottom:10,}}>类型</Text>
                                <View style={{flexWrap:'wrap',flexDirection: 'row',justifyContent: 'flex-start',marginBottom:10,marginLeft:15,paddingRight:15,}}>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,11)}>
                                        <View><Text style={this.state.modelItem.has(11)!==true?styles.modelText:styles.selectModelText}>全部</Text></View>
                                    </TouchableOpacity>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,1)}>
                                        <View><Text style={this.state.modelItem.has(1)!==true?styles.modelText:styles.selectModelText}>请假</Text></View>
                                    </TouchableOpacity>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,4)}>
                                        <View><Text style={this.state.modelItem.has(4)!==true?styles.modelText:styles.selectModelText}>地址变更</Text></View>
                                    </TouchableOpacity>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,5)}>
                                        <View><Text style={this.state.modelItem.has(5)!==true?styles.modelText:styles.selectModelText}>手机号变更</Text></View>
                                    </TouchableOpacity>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,6)}>
                                        <View><Text style={this.state.modelItem.has(6)!==true?styles.modelText:styles.selectModelText}>银行卡变更</Text></View>
                                    </TouchableOpacity>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,2)}>
                                        <View><Text style={this.state.modelItem.has(2)!==true?styles.modelText:styles.selectModelText}>晋级</Text></View>
                                    </TouchableOpacity>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,3)}>
                                        <View><Text style={this.state.modelItem.has(3)!==true?styles.modelText:styles.selectModelText}>复效</Text></View>
                                    </TouchableOpacity>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,0)}>
                                        <View><Text style={this.state.modelItem.has(0)!==true?styles.modelText:styles.selectModelText}>离职</Text></View>
                                    </TouchableOpacity>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,7)}>
                                        <View><Text style={this.state.modelItem.has(7)!==true?styles.modelText:styles.selectModelText}>收入证明</Text></View>
                                    </TouchableOpacity>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,8)}>
                                        <View><Text style={this.state.modelItem.has(8)!==true?styles.modelText:styles.selectModelText}>工作证明</Text></View>
                                    </TouchableOpacity>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,9)}>
                                        <View><Text style={this.state.modelItem.has(9)!==true?styles.modelText:styles.selectModelText}>其他证明</Text></View>
                                    </TouchableOpacity>
                                    <TouchableOpacity style={{}} onPress={this.clickModelItem.bind(this,10)}>
                                        <View><Text style={this.state.modelItem.has(10)!==true?styles.modelText:styles.selectModelText}>其他</Text></View>
                                    </TouchableOpacity>

                                </View>
                                <Button title='确定' onPress={this.clickModel.bind(this)}/>
                            </View>
                        </View>
                    </Modal>
                </View>

            </View>
        );
    }
    /**
     * 下拉属性
     */
    onRefresh = () => {
        this.fetchData(1,false);
        this.setState( {
            refreshing:false,
            myApplyToBeAuditedAllLoaded:false,
        });
        pageNo=1;
    };
    _renderFooter(){
        if (this.state.showFoot === 1) {
            return (
                <View style={{height:50,alignItems:'center',justifyContent:'flex-start',}}>
                    <Text style={{color:'#999999',fontSize:14,marginTop:5,marginBottom:10,}}>
                        没有更多数据了
                    </Text>
                </View>
            );
        } else if(this.state.showFoot === 2) {
            return (
                <View style={styles.footer}>
                    <ActivityIndicator />
                    <Text>正在加载更多数据...</Text>
                </View>
            );
        } else if(this.state.showFoot === 0){
            return (
                <View style={styles.footer}>
                    <Text>上拉加载更多数据...</Text>
                </View>
            );
        }
    }
    _onEndReached(){
        //如果当前页大于或等于总页数，那就是到最后一页了，返回
        // if(pageNo!=1) {
        //     pageNo++;
        // }
        if(!this.state.myApplyToBeAuditedAllLoaded){
            pageNo++;
            this.fetchData(pageNo,true);
            this.setState({
                showFoot:2,
            });
        }
        // this.fetchData(pageNo,true);
        // alert('shangla');
        // //如果是正在加载中或没有更多数据了，则返回
        // if(this.state.showFoot != 0 ){
        //     return ;
        // }
        // //如果当前页大于或等于总页数，那就是到最后一页了，返回
        // if((pageNo!=1) && (pageNo>=totalPage)){
        //     return;
        // } else {
        //     pageNo++;
        // }
        // //底部显示正在加载更多数据
        // this.setState({showFoot:2});
        // //获取数据
        // this.fetchData( pageNo );
    }
}

const styles = StyleSheet.create({
    modelText:{marginBottom:10,textAlign:'center',marginLeft:20,borderRadius:5,borderWidth:0.5,borderColor:'black',padding:5,height:30,backgroundColor:'#F2F2F2',color:'#000000'},
    selectModelText:{marginBottom:10,textAlign:'center',marginLeft:20,borderRadius:5,borderWidth:0.5,borderColor:'black',padding:5,height:30,backgroundColor:'#169BD5',color:'#ffffff'},
    footer:{flexDirection:'row', height:50, justifyContent:'center', alignItems:'center', marginBottom:10,marginTop:10,},
});