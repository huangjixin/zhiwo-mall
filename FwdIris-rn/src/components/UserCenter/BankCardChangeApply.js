import React from 'react';
import {StyleSheet,Dimensions, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,ImageBackground,DeviceEventEmitter,TouchableOpacity,FlatList} from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import {FwdLoading} from "./FwdLoading";
import * as RequestURL from "../../common/RequestURL";
import * as FetchUtils from "../../common/FetchUtils";
import * as NumberUtils from "../../common/NumberUtils";

var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

const g_agentCode = '888999';   //暂时用于显示当前职级
export class BankCardChangeApply extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            isLoading:false,
            data:[],
        }
    }
    componentWillMount() {
        this.fetchData();
    }

    // 网络请求
    fetchData() {

        const params = {
            agentCode:g_agentCode,
        }
        this.setState({isLoading:true});
        FetchUtils.Get({
            url:RequestURL.BANKCARD_LIST,
            params:params,
            success:(respData)=>{
                const data = respData.data.map((rowData) => {
                    rowData.hiddenCardNo =NumberUtils.hiddenBankCard(rowData.cardNo);
                    const cradStyle = this.getCardStyle(rowData.cardType);
                    return {...rowData,...cradStyle};
                });

                this.setState({
                    data :data,
                    isLoading:false
                });

            },
            error:()=>{
                this.setState({isLoading:false});
            }
        })
    }


    getCardStyle = (cardType) =>{
        let ret = null;
        switch (cardType) {
            case '1' :
                ret = {
                    icon: require('../../../img/UserCenter/CMB.gif'),
                    backgroundColor: {backgroundColor: '#FFDD00'}
                }
                break;
            case '2' :
                ret = {
                    icon: require('../../../img/UserCenter/BOCOM.gif'),
                    backgroundColor: {backgroundColor: '#37B5FF'}
                }
                break;
            case '3' :
                ret = {
                    icon: require('../../../img/UserCenter/ICBC.gif'),
                    backgroundColor: {backgroundColor: '#FF8090'}
                }
                break;
            default :
                ret = {
                    icon: require('../../../img/UserCenter/BOCOM.gif'),
                    backgroundColor: {backgroundColor: '#37B5FF'}
                };
                break;
        }
        return ret;
    }

    AddBankCardPage=()=>{
        DeviceEventEmitter.emit('AddBankCardPage'); //银行变更申请-》添加银行页面
    }

    BankCardReleaseBindingDetailsPage=(bankDetail)=>{
        if(!bankDetail.isDefault)
            DeviceEventEmitter.emit('BankCardReleaseBindingDetailsPage',bankDetail); //银行变更申请-》银行详情页面
    }

    render() {
        const {data,isLoading} = this.state;
        return (
            <ScrollView style={{backgroundColor:'#FFFFFF'}}>
                <View>

                    <ApplyCommonHeader title={'变更银行卡'} onReturn={() => this.props.navigation.goBack()}/>

                    <FwdLoading isLoading={isLoading}/>

                    {data.map((rowData, i,array) => (
                        <TouchableWithoutFeedback key={i} onPress={()=>{this.BankCardReleaseBindingDetailsPage(rowData)}}>
                            <View style={[rowData.backgroundColor,{height:100,marginTop:20,marginLeft:20,marginRight:20,borderRadius:10,
                                shadowColor:'#FFDD00',shadowOpacity: 0.1,shadowRadius: StyleSheet.hairlineWidth,
                                shadowOffset: {
                                    width: StyleSheet.hairlineWidth,
                                    height: StyleSheet.hairlineWidth,
                                },elevation: 4}]}
                        >
                                <View style={{flexDirection: 'row',marginLeft:30, marginTop:15}}>
                                    <View style={{flex:1}}>
                                        <Image style={{width:35,height:35,}}source={rowData.icon}/>
                                    </View>
                                    <Text style={{flex:6,height:35,lineHeight:35,paddingLeft:10,color:'#000000',fontSize:16}}>{rowData.description}</Text>

                                    {rowData.isDefault && (
                                        <View style={{flex:2,height:35,flexDirection: 'column',justifyContent: 'center'}}>
                                            <Text style={{fontSize:14,height:25,lineHeight:25,textAlign:'center',backgroundColor:'#ffffff',width:50,borderRadius:20,color:'#000000',fontSize:16}}>默认</Text>
                                        </View>
                                    )}

                                </View>
                                <View style={{flexDirection: 'row',marginLeft:35, marginTop:10,}}>
                                    <Text style={{fontSize:20,color:'#000000',fontWeight:'300',fontSize:20}}>{rowData.hiddenCardNo}</Text>
                                </View>
                            </View>
                        </TouchableWithoutFeedback>
                    ))}

                    <View style={{height:100,marginTop:20,marginLeft:20,marginRight:20,borderRadius:10}}>
                        <TouchableWithoutFeedback  onPress={this.AddBankCardPage}>
                            <ImageBackground style={{ flex:1,height:100}} source={require('../../../img/UserCenter/addBank.png')}></ImageBackground>
                        </TouchableWithoutFeedback>
                    </View>
                </View>
                <View style={{marginTop:20}}></View>
            </ScrollView>
        );
    }
}

