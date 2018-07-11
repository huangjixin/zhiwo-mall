import React from 'react';
import {StyleSheet, ScrollView, View, Text, Image, Dimensions,TouchableWithoutFeedback,RefreshControl,AsyncStorage} from 'react-native';
import {AchvCollapsible} from "./AchvCollapsible";
import Echarts from 'native-echarts';
import * as NumberUtils from "../../common/NumberUtils";
import * as RequestURL from "../../common/RequestURL";
import * as FetchUtils from "../../common/FetchUtils";
import Picker from 'react-native-picker';
import Toast from "../UserCenter/Toast/Toast";

const g_agentGrade = 'AD';   //暂时用于显示当前职级
const g_agentCode = '10000792';   //暂时用于显示当前职级
export class AchvTabIncome extends React.Component {

    defaultData = {
        preTax:0,
        afterTax:0,
        taxAmount:0,
        detailList:[],
    }

    constructor(props){
        super(props)
        this.state={
            isRefreshing:true,
            selectIndex:null,
            echartsOption:{},
            queryYear:(new Date()).getUTCFullYear(),
            queryMonth:(new Date()).getUTCMonth(),
            data:{
                preTax:0,
                afterTax:0,
                taxAmount:0,
                detailList:[],
            }
        }
    }
    fetchData() {
        const params = {
            agentCode:g_agentCode,
            queryDate:this.getQueryDate(),
        }
        this.setState({isRefreshing:true});
        FetchUtils.Get({
            url:RequestURL.MY_INCOME,
            params:params,
            success:(respData)=>{
                if(respData.code!='1'){
                    this.setState({isRefreshing:false})
                    Toast.show('请求错误',Toast.LONG);
                    return;
                }

                let data = respData.data;
                if(data==null){
                    data = this.defaultData;
                }

                if(data.detailList == null)
                    data.detailList = [];
                const option = this.convertToEchartsOption(data.detailList);

                this.setState({
                    echartsOption:option,
                    data:data,
                    isRefreshing:false
                });

                //set cache
                const key = 'AchvTabIncome:'+g_agentCode;
                AsyncStorage.setItem(key,JSON.stringify(data));
            },
            error:(isTimeOut)=>{
                if(isTimeOut){
                    Toast.show("请求超时",Toast.LONG);
                }else{
                    Toast.show("未知错误",Toast.LONG);
                }
                this.setState({isRefreshing:false})
            }
        })
    }
    componentWillMount(){
        //get Cache
        const key = 'AchvTabIncome:'+g_agentCode;
        AsyncStorage.getItem(key).then((result)=> {
                if (result == null) {
                    return;
                }
                const cacheData = JSON.parse(result);
                const option = this.convertToEchartsOption(cacheData.detailList);
                this.setState({
                    echartsOption:option,
                    data:cacheData,
                });
            }).catch((error)=> {
                console.log('error:' + error.message);
            });

        this.fetchData();
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

    renderHeader = (data,collapsed,isLast) => (
        <View style={[styles.itemContainer,
            !collapsed?
                styles.expandingStyle
                :!isLast && styles.collapsedStyle]}>
            <View style={{flex:2,paddingLeft:15}}>
                <Text style={{fontSize:18,color:'#4A4A4A'}}>{data.category}</Text>
            </View>
            <View style={{paddingRight:10,flex:1,flexDirection:'row',justifyContent: 'flex-end',alignItems:'center'}}>
                <Text style={{fontSize:18,color:'#E87722'}}>{NumberUtils.toLargeMoneyStr(data.amount)}</Text>
                {collapsed ? (
                    <Image source={require('../../../img/achievement/incomeDown.png')}
                           style={{width: 10, height: 10, marginLeft: 10}}/>
                ):(
                    <Image source={require('../../../img/achievement/incomeUp.png')}
                           style={{width: 10, height: 10, marginLeft: 10}}/>
                )
                }
            </View>
        </View>
     );

    renderBlock = (data) => (
        <View style={{backgroundColor:'#FBFBFB',paddingBottom:10
            ,borderBottomWidth:2, borderBottomColor:'#F4F4F4'
            ,borderLeftWidth:10,borderLeftColor:'#FFDD00'}}>
            {data.subList!=null &&
                data.subList.map((v, i) => (
                    <View key={i} style={{flexDirection:'row',justifyContent: 'center',alignItems: 'center',marginTop:10,}}>
                        <View style={{flex:2,paddingLeft:15}}>
                            <Text style={{fontSize:14,color:'#4A4A4A'}}>{v.subCategory}</Text>
                        </View>
                        <View style={{paddingRight:30,flex:1,flexDirection:'row',justifyContent: 'flex-end',alignItems:'center'}}>
                            <Text style={{fontSize:16,color:'#878787'}}>{NumberUtils.toLargeMoneyStr(v.subAmount)}</Text>
                        </View>
                    </View>
                ))
            }
        </View>
    );



    sectionSelect = (data)=>{
        // console.log(data.dataIndex);
        if(data!=null && data.dataIndex!=null){
            this.setState({
                selectIndex:data.dataIndex
            })
        }
    }

    onRefresh() {
        this.fetchData();
    }

  render() {

    const {selectIndex,data,echartsOption} = this.state;

    return (
        <ScrollView style={{backgroundColor:'#FBFBFB'}}
            refreshControl={  //设置下拉刷新组件
                <RefreshControl
                    refreshing={this.state.isRefreshing}
                    onRefresh={this.onRefresh.bind(this)}  //(()=>this.onRefresh)或者通过bind来绑定this引用来调用方法
                />
            }
        >
	      {/*月份*/}
	      <View style={{flexDirection:'row',marginTop:15,marginLeft:10}}>
              <TouchableWithoutFeedback onPress={this._showDatePicker.bind(this)}>
                <View style={{alignItems: 'center',flexDirection:'row'}}>
                      <Text style={{color:'#4A4A4A',fontSize:14}}>
                          {this.state.queryYear}年
                          {this.state.queryMonth}月收入
                      </Text>
                      <Image  source={require('../../../img/achievement/imcomeDate.png')}
                              style={{width: 10, height:10,}} />
                </View>
              </TouchableWithoutFeedback>
              <View style={{flex:1}}></View>
          </View>

	      {/*收入总数*/}
            <View style={{marginTop:10,marginLeft:10,marginRight:10
                ,backgroundColor:'#FFFFFF',height:380
                ,paddingLeft:10,paddingTop:10,borderRadius:20}}>
                <View>
                    <Text style={{color:'#000000',fontSize:24,fontWeight:'bold'}}>{NumberUtils.toLargeMoneyStr(data.afterTax,2)}</Text>
                    <Text style={{fontSize:16,color:'#4A4A4A',paddingTop:5,}}>税前 {NumberUtils.toLargeMoneyStr(data.preTax,2)}</Text>
                    <Text style={{fontSize:16,color:'#4A4A4A',paddingTop:5,}}>税收 {NumberUtils.toLargeMoneyStr(data.taxAmount,2)}</Text>
                </View>
                <View style={{flexDirection:'row',marginTop:10}}>
                    <View style={{flex:1,justifyContent: 'center',alignItems: 'center',}}>
                        <Echarts
                            option={echartsOption}
                            height={Dimensions.get('window').width/2*3}
                            width={Dimensions.get('window').width}
                            onPress={(data)=>{this.sectionSelect(data)}}
                        />
                    </View>
                </View>

            </View>

	      {/*收入详细*/}
            <View style={{marginTop:10,marginLeft:10,marginRight:10}}>
	            <Text style={{marginLeft:5,marginBottom:10,fontSize:14,color:'#A8A7A7'}}>收入明细</Text>

                <View style={{borderRadius:20,backgroundColor:'#FFFFFF'}}>
                    {
                        data.detailList.map((rowData, i,arr) => (
                                <AchvCollapsible
                                    key={i}
                                    data = {rowData}
                                    renderHeader={(data,collapsed)=>this.renderHeader(data,collapsed,arr.length==(i+1))}
                                    renderBlock={this.renderBlock}
                                    isCollapsed={!(i==selectIndex)}
                                    pressCallback={()=>{
                                        if(selectIndex==null)
                                            this.setState({selectIndex:i})

                                        if(selectIndex==i)
                                            return
                                        this.setState({selectIndex:i})
                                    }}
                                />
                            ))
                    }
                </View>
            </View>
            <View style={{marginTop:50}}/>
        </ScrollView>
    );
  }

    convertToEchartsOption=(detailList)=>{

      if(detailList==null)
        detailList = [];

      const showData = detailList.map((item)=>{
          return {'value':item.amount,'name':item.category};
      });
      const legendData = detailList.map((item)=>{
          return item.category;
      });

      const option = this.initEchartsOption();
      option.legend.data = legendData;
      option.series[0].data = showData;

      return option;
  }

  initEchartsOption=()=>{
        return {
            legend: {
                orient : 'horizontal',
                x : 'center',
                y: 'bottom',
                padding:[0,0,230,0],
                data:[]
            },
            series : [
                {
                    name:'income-chart',
                    type:'pie',
                    radius : ['30%', '45%'],//饼图的半径大小
                    center: ['50%', '26%'],//饼图的位置
                    data:[],
                    itemStyle:{
                        normal:{
                            label:{
                                show:false,
                            },
                            labelLine:{
                                show:false,
                            }
                        },
                        emphasis:{
                            label:{
                                show: true,
                                formatter:'{d}%',
                                textStyle:{
                                    color:'#A8A7A7',
                                    fontSize:14,
                                    align:'left',
                                    baseline:'bottom'
                                }
                            },
                            labelLine :{
                                show:true,
                                length:60,
                                lineStyle:{
                                    color:'#D5D5D5'
                                }
                            }
                        }
                    }
                }
            ],
            color: ['#FFDD00','#FFAC6D','#AEA9FD','#FF807D','#77B6FF','#7AEBCA']
        };
  }
}

const styles = StyleSheet.create({

    header: {
        backgroundColor: '#F5FCFF',
        paddingTop: 10,
    },

    collapsedStyle:{
        borderBottomWidth:2,
        borderBottomColor:'#F4F4F4',
    },
    expandingStyle:{
        backgroundColor:'#FBFBFB',
        borderLeftWidth:10,
        borderLeftColor:'#FFDD00',
    },
    itemContainer:{
        height:60,
        flexDirection:'row',
        justifyContent: 'center',
        alignItems: 'center',
    },
});

