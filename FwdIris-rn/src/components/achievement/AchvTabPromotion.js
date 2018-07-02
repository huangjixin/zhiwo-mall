import React from 'react';
import {
    StyleSheet, Text, View, Image, TouchableWithoutFeedback, ScrollView, RefreshControl,
    AsyncStorage
} from 'react-native';
import * as Progress from 'react-native-progress';
import * as NumberUtils from "../../common/NumberUtils";
import * as RequestURL from "../../common/RequestURL";
import * as FetchUtils from "../../common/FetchUtils";

const g_agentCode = '00001';   //暂时用于显示当前职级
const g_agentGrade = 'SM';   //暂时用于显示当前职级
const IDX_PROMOTION = 0;
const IDX_EVALUATION = 1;

export class AchvTabPromotion extends React.Component {
    //晋升路径
    promotionCache = [];
    //考核
    evaluationCache = {};

    constructor(props) {
        super(props);
        this.state = {
            isRefreshing:true,
            promotionPostIndex: 0,  //当前代理人  晋升方向选择
            selectedIndex: IDX_PROMOTION,  // 选中(0:晋升 1:考核 )
            agentGrade:{
                "gradeCode": "",
                "gradeName": ""
            },
            promotionDirect:[
                {
                    "gradeCode": "",
                    "gradeName": ""
                }],
            data:{
                assessPeriodFrom: '',
                assessPeriodTo: '',
                nextAssessDay:'',
                nextAssessHour:'',
                nextAssessMinus:'',
                indicatorList: [],
            }
        }
    }

    fetchData() {
        const params = {
            agentCode:g_agentCode,
        }
        this.setState({isRefreshing:true,})
        FetchUtils.Get({
            url:RequestURL.RANK_PROMOTION,
            params:params,
            success:(respData)=>{
                const data = respData.data;
                /**/
                const gradeObj = gradeMap[g_agentGrade];
                /**/

                this.promotionCache[0]=data;
                this.setState({
                    agentGrade:gradeObj.agentGrade,
                    promotionDirect:gradeObj.direction,
                    data:data,
                    isRefreshing:false,
                });

                //set cache
                const key = 'AchvTabPromotion:'+g_agentCode;
                AsyncStorage.setItem(key,JSON.stringify(data));
            },
            error:()=>{
                this.setState({isRefreshing:false,})
            }
        })

        this.timer = setInterval(() => {
            const curVal = this.state.data.nextAssessMinus -1;
            const data = this.state.data;
            data.nextAssessMinus = curVal;
            if(curVal==-1){
                clearInterval(this.timer);
                this.timer = null;
                return;
            }
            this.setState({data:data});
        }, 60000);
    }

    componentWillMount(){
        //get Cache
        const key = 'AchvTabPromotion:'+g_agentCode;
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
    }

    isEmptyData = (data)=>{
        return Object.keys(data).length==0;
    }


    switchTab = (index) => {

        if(index==this.state.selectedIndex)
            return;

        if(index == IDX_PROMOTION){
            const promIndex = this.state.promotionPostIndex;
            const promotionData = this.promotionCache[promIndex];
            this.setState({
                selectedIndex: index,
                data:promotionData
            })
        }else {
            const evaluationData = this.evaluationCache;
            if(this.isEmptyData(evaluationData)){
                const params = {
                    agentCode: g_agentCode,
                }
                this.setState({isRefreshing:true})
                FetchUtils.Get({
                    url:RequestURL.RANK_EVALUATION,
                    params: params,
                    success:(resp) => {
                        const respData = resp.data;
                        this.evaluationCache = respData;
                        this.setState({
                            selectedIndex: index,
                            data:respData,
                            isRefreshing:false
                        })
                    },
                    error:()=>{
                        this.setStaet({isRefreshing:false})
                    }
                } )
            }else{
                this.setState({
                    selectedIndex: index,
                    data:evaluationData
                })
            }
        }

    }

    switchPromotionDirect = (index,gradeCode) => {

        if(index==this.state.promotionPostIndex)
            return;

        const cacheData = this.promotionCache[index];
        if(typeof cacheData != 'undefined'){
            this.setState({
                promotionPostIndex: index,
                data:cacheData
            });
        }else {
            const {data} = this.state;
            const agentGrade = data.agentGrade;
            const line = agentGrade.gradeCode+ '-' + gradeCode;
            const params = {
                agentCode: g_agentCode,
                line:line,
            }
            this.setState({isRefreshing:true})
            FetchUtils.Get({
                url:RequestURL.RANK_PROMOTION,
                params:params,
                success:(resp) => {
                    const respData = resp.data;
                    this.promotionCache[index] = respData;
                    this.setState({
                        promotionPostIndex: index,
                        data: respData,
                        isRefreshing:false,
                    });
                },
                error:()=>{
                    this.setState({isRefreshing:false});
                }
            })
        }
    }

    onRefresh = ()=>{

        this.setState({isRefreshing: true});

        const selectedIndex = this.state.selectedIndex;
        const  promotionPostIndex= this.state.promotionPostIndex;

        this.setState({isRefreshing:true})

        if(selectedIndex == IDX_PROMOTION){
            const {data,agentGrade,promotionDirect} = this.state;
            const fromGrade = agentGrade.gradeCode;
            const toGrade = promotionDirect[promotionPostIndex].gradeCode
            const line = fromGrade + '-' + toGrade;
            const params = {
                agentCode: g_agentCode,
                line:line,
            }
            FetchUtils.Get({
                url:RequestURL.RANK_PROMOTION,
                params:params,
                success:(resp) => {
                    const respData = resp.data;
                    this.promotionCache[promotionPostIndex] = respData;
                    this.setState({
                        data: respData,
                        isRefreshing:false
                    });
                },
                error:()=>{
                    this.setState({isRefreshing:false});
                }
            })
        }else{
            const params = {
                agentCode: g_agentCode,
            }
            FetchUtils.Get({
                url:RequestURL.RANK_EVALUATION,
                params:params,
                success:(resp) => {
                    const respData = resp.data;
                    this.evaluationCache = respData;
                    this.setState({
                        data:respData,
                        isRefreshing:false
                    })
                },
                error:()=>{
                    this.setState({isRefreshing:false})
                }
            })
        }

    }

    renderItemDetail = (data,index) =>(
        <View key={index} style={{height:100,paddingLeft:20,paddingTop:10,paddingRight:20
            ,flexDirection:'column',}}>
            <View style={{flexDirection:'row',flex:4}}>
                <View style={{flex:1,flexDirection:'column'}}>
                    <View style={{flex:1}}>
                        <Text style={{fontSize:16,color:'#A8A7A7'}}>{data.categroy}</Text>
                    </View>
                    <View style={{flex:1}}>
                        <Text style={{fontSize:22,color:'#333333'}}>{NumberUtils.toLargeMoneyStr(data.amount)}</Text>
                    </View>
                </View>
                <View style={{
                    flex:1,
                    flexDirection: 'row',
                    justifyContent: 'flex-end',
                }}>
                    <View style={{flexDirection:'column'}}>
                        <View style={{flex:1}}>
                            <Text style={{fontSize:18,color:'#878787'}}>目标 : {NumberUtils.toLargeMoneyStr(data.target)}</Text>
                        </View>
                        <View style={{flex:1}}>
                            <Text style={{fontSize:18,color:'#878787'}}>差额 :
                                <Text style={{color:'#E77520'}}> {NumberUtils.toLargeMoneyStr(data.balance)}</Text>
                            </Text>
                        </View>
                    </View>
                </View>
            </View>
            <View style={{flex:1}}>
                <Progress.Bar progress={data.percentage}
                              width={null} height={2}
                              borderWidth={0} borderRadius={1}
                              color={'#FFDD00'} unfilledColor={'#EAEAEA'}
                />
            </View>
    </View>
    )

    render() {
        const {data,selectedIndex} = this.state;
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}
                refreshControl={  //设置下拉刷新组件
                    <RefreshControl
                        refreshing={this.state.isRefreshing}
                        onRefresh={this.onRefresh.bind(this)
                        }  //(()=>this.onRefresh)或者通过bind来绑定this引用来调用方法
                    />
                }
            >

                {/*Tab - 晋升或考核*/}
                <View style={{flexDirection:'row',marginTop:15,marginLeft:14,marginRight:14}}>
                    {/*//根据晋升渠道来判断要不要显示晋升(0：不显示)*/}
                    <TouchableWithoutFeedback onPress={()=>this.switchTab(IDX_PROMOTION) }>
                        <View style ={{marginLeft:4,backgroundColor:selectedIndex==IDX_PROMOTION?'#FFDD00':'#FFFFFF',
                            borderRadius:5,flex:1,height:35,justifyContent: 'center',alignItems: 'center'}}>
                            {/*<Text style={{fontSize:19,fontFamily:'MicrosoftYaHei'}}>晋升</Text>*/}
                            <Text style={{fontSize:19,}}>晋升</Text>
                        </View>
                    </TouchableWithoutFeedback>

                    <TouchableWithoutFeedback onPress={()=>this.switchTab(IDX_EVALUATION)}>
                        <View style ={{marginLeft:4,backgroundColor:selectedIndex==IDX_EVALUATION?'#FFDD00':'#FFFFFF',
                            borderRadius:5,flex:1,height:35,justifyContent: 'center',alignItems: 'center'}}>
                            {/*<Text style={{fontSize:19,fontFamily:'MicrosoftYaHei'}}>考核</Text>*/}
                            <Text style={{fontSize:19,}}>考核</Text>
                        </View>
                    </TouchableWithoutFeedback>
                </View>

                {/*考核周期*/}
                <View style={{borderRadius:10,borderColor:'#E6E6E6',borderWidth:1,borderStyle:'dotted'
                    ,height:76,flexDirection:'column',marginLeft:14,marginRight:14
                    ,paddingLeft:20,paddingTop:8,paddingBottom:8,marginTop:18}}>

                    <View style={{flexDirection:'row',alignItems: 'center',flex:1}}>
                        <Image  source={require('../../../img/achievement/period.png')}
                                style={{width: 20, height:20,}} />
                        <Text style={{fontSize:16,marginLeft:8}}>考核周期
                            <Text style={{color:'#000000'}}>  {data.assessPeriodFrom}~{data.assessPeriodTo}</Text>
                        </Text>
                    </View>
                    <View style={{flexDirection:'row',alignItems: 'center',flex:1}}>
                        <Image  source={require('../../../img/achievement/nextTime.png')}
                                style={{width: 20, height:22,}} />
                        <Text style={{fontSize: 16,marginLeft:8}}>

                            {selectedIndex==IDX_PROMOTION?'距离下次晋升时间：':'距离下次考核时间：'}

                            <Text style={{color: 'red'}}>{data.nextAssessDay}</Text>
                            天
                            <Text style={{color: 'red'}}>{data.nextAssessHour}</Text>
                            时
                            <Text style={{color: 'red'}}>{data.nextAssessMinus}</Text>
                            分
                        </Text>
                    </View>
                </View>

                {/*晋升方向*/}
                {
                    selectedIndex==IDX_PROMOTION && this.renderPromotion()
                }

                <View style={{marginTop:10,marginLeft:14,marginRight:14,borderRadius:10,
                    paddingBottom:10,paddingTop:10,backgroundColor:'#FFFFFF'
                }}>
                    {/*业绩详细*/}
                    {
                        data.indicatorList.map((rowData, i) => (
                            this.renderItemDetail(rowData, i)
                        ))
                    }
                </View>
                <View style={{marginTop:50}}/>
            </ScrollView>
        );
    }


    renderPromotion=()=>{
        const {data,promotionPostIndex,agentGrade,promotionDirect} = this.state;
        return(
            <View style={{marginTop:10,marginLeft:14,marginRight:14}}>
                {promotionDirect.length==2 && (
                    <View style={{flexDirection:'row',marginBottom:10}}>
                        <TouchableWithoutFeedback onPress={()=>this.switchPromotionDirect(0,promotionDirect[0].gradeCode)}>
                            <View style={{flexDirection:'row',flex:1,justifyContent: 'center',alignItems: 'center'}}>
                                <View style={promotionPostIndex==0?styles.selectStyle:styles.normalStyle}>
                                    <Text style={{fontSize:16,color:promotionPostIndex==0?'#4A4A4A':'#B2B2B2'}}>{promotionDirect[0].gradeCode}</Text>
                                </View>
                            </View>
                        </TouchableWithoutFeedback>

                        <TouchableWithoutFeedback onPress={()=>this.switchPromotionDirect(1,promotionDirect[1].gradeCode)}>
                            <View style={{flexDirection:'row',flex:1,justifyContent: 'center',alignItems: 'center'}}>
                                <View style={promotionPostIndex==1?styles.selectStyle:styles.normalStyle}>
                                    <Text style={{fontSize:16,color:promotionPostIndex==1?'#4A4A4A':'#B2B2B2'}}>{promotionDirect[1].gradeCode}</Text>
                                </View>
                            </View>
                        </TouchableWithoutFeedback>
                    </View>
                )}


                <View style={{backgroundColor:'#FFFFFF',borderRadius:10
                    ,height:115,flexDirection:'row'}}>
                    <View style={{flex:3,justifyContent: 'center',alignItems: 'center',}}>
                        <Text style={{fontSize:25,color:'#000000'}}>{agentGrade.gradeCode}</Text>
                        <Text style={{fontSize:14,marginTop:10,color:'#4A4A4A'}}>{agentGrade.gradeName}</Text>
                    </View>
                    <View style={{flex:1,justifyContent: 'center',alignItems: 'center',}}>
                        <Image  source={require('../../../img/achievement/promDirec.png')}
                                style={{width: 40, height:20,}} />
                    </View>

                    {/*根据选中晋升渠道来显示哪个晋升渠道*/}
                    {promotionPostIndex==0?(
                        <View style={{flex:3,justifyContent: 'center',alignItems: 'center',}}>
                            <Text style={{fontSize:25,color:'#B2B2B2'}}>{promotionDirect[0].gradeCode}</Text>
                            <Text style={{fontSize:14,marginTop:10,color:'#B2B2B2'}}>{promotionDirect[0].gradeName}</Text>
                        </View>
                    ):(
                        <View style={{flex:3,justifyContent: 'center',alignItems: 'center',}}>
                            <Text style={{fontSize:25,color:'#B2B2B2'}}>{promotionDirect[1].gradeCode}</Text>
                            <Text style={{fontSize:14,marginTop:10,color:'#B2B2B2'}}>{promotionDirect[1].gradeName}</Text>
                        </View>
                    )}


                </View>

            </View>
        )
    }
}

const gradeMap = {
    LA:{
        agentGrade:{
            "gradeCode": "LA",
            "gradeName": "寿险顾问"
        },
        direction:[
            {
                "gradeCode": "AM",
                "gradeName": "业务经理"
            },
            {
                "gradeCode": "SM",
                "gradeName": "销售经理"
            },
        ]
    },
    SM:{
        agentGrade:{
            "gradeCode": "SM",
            "gradeName": "寿险顾问"
        },
        direction:[
            {
                "gradeCode": "SSM",
                "gradeName": "资深销售经理"
            },
            {
                "gradeCode": "AM",
                "gradeName": "业务经理"
            },
        ]
    },
    SSM:{
        agentGrade:{
            "gradeCode": "SSM",
            "gradeName": "资深销售经理"
        },
        direction:[
            {
                "gradeCode": "AM",
                "gradeName": "业务经理"
            },
            {
                "gradeCode": "SD",
                "gradeName": "销售总监"
            },
        ]
    },
    AM:{
        agentGrade:{
            "gradeCode": "AM",
            "gradeName": "业务经理"
        },
        direction:[
            {
                "gradeCode": "SAM",
                "gradeName": "资深业务经理"
            }
        ]
    },
    SAM:{
        agentGrade:{
            "gradeCode": "SAM",
            "gradeName": "资深业务经理"
        },
        direction:[
            {
                "gradeCode": "AD",
                "gradeName": "总监"
            }
        ]
    },
    SD:{
        agentGrade:{
            "gradeCode": "SD",
            "gradeName": "销售总监"
        },
        direction:[
            {
                "gradeCode": "AM",
                "gradeName": "业务经理"
            }
        ]
    },
    AD:{
        agentGrade:{
            "gradeCode": "AD",
            "gradeName": "总监"
        },
        direction:[]
    },
};
const styles = StyleSheet.create({
    normalStyle: {width: 40, justifyContent: 'center', alignItems: 'center'},
    selectStyle: {
        width: 40,
        borderBottomColor: '#FFDD00',
        borderBottomWidth: 2,
        justifyContent: 'center',
        alignItems: 'center'
    },
});
