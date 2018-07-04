import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,DeviceEventEmitter,FlatList,Dimensions,TouchableOpacity} from 'react-native';
import CheckBox from 'react-native-checkbox';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import {FwdLoading} from "./FwdLoading";
import * as FetchUtils from "../../common/FetchUtils";
import * as RequestURL from "../../common/RequestURL";
import * as NumberUtils from "../../common/NumberUtils";
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
const g_agentCode = '10000792';
export class AddressChangeApply extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            defaultAddress:null,
            otherAddress:[],
        }
    }
    componentWillMount() {
        this.fetchData();
    }

    AddAddressPage=(item)=>{
       // DeviceEventEmitter.emit('AddAddressPage',item); //地址变更申请-》添加地址页面
        DeviceEventEmitter.emit('AddAddressPage'); //地址变更申请-》添加地址页面
    }

    modifyAddressPage=(item)=>{
        // DeviceEventEmitter.emit('AddAddressPage',item); //地址变更申请-》添加地址页面
        DeviceEventEmitter.emit('AddAddressPage',item); //地址变更申请-》添加地址页面
    }

    // 网络请求
    fetchData() {
        const params = {
            agentCode:g_agentCode,
        }
        this.setState({isLoading:true});
        FetchUtils.Get({
            url:RequestURL.ADDRESS_LIST,
            params:params,
            success:(respData)=>{
                const defaultAddress = respData.data.filter(item=>{
                    return item.isDefault;
                });

                const otherAddress = respData.data.filter(item=>{
                    return !item.isDefault;
                });

                const len = defaultAddress.length;
                this.setState({
                    defaultAddress:len==0?null:defaultAddress[0],
                    otherAddress:otherAddress,
                    isLoading:false
                });

            },
            error:()=>{
                this.setState({isLoading:false});
            }
        })
    }

    render() {

        const {defaultAddress,otherAddress,isLoading} = this.state;

        return (
            <ScrollView style={{backgroundColor:'#FBFBFB',}}>
                <ApplyCommonHeader
                    title={'我的地址'}
                    onReturn={() => this.props.navigation.goBack()}
                    actionName={'新增地址'}
                    onAction={() => this.AddAddressPage()}
                />
                <FwdLoading isLoading={isLoading}/>
                <View  style={{marginTop:20}}></View>

                {defaultAddress !=null &&(
                    <View  style={{flexDirection: 'row',justifyContent: 'center',backgroundColor:'#ffffff',paddingLeft:15,paddingRight:10,
                        height:80,marginLeft:15,marginRight:15,marginBottom:15,
                        borderRadius:10,}}>
                        <View style={{flex:5,flexDirection: 'column',justifyContent: 'center',}}>
                            <Text style={{color:'#9B9B9B',marginBottom:5,fontSize:13}}>当前使用地址</Text>
                            <Text style={{color:'#4A4A4A',fontSize:16,}}>
                                <Text>{defaultAddress.province}</Text>
                                <Text>{defaultAddress.city}</Text>
                                <Text>{defaultAddress.area}</Text>
                                <Text>{defaultAddress.addressDetail}</Text>
                            </Text>
                        </View>
                        <TouchableOpacity onPress={()=>{this.modifyAddressPage(defaultAddress)}} style={{justifyContent:'center',alignItems: 'center',flex:1}}>
                            <View style={{flex:1,justifyContent: 'center',alignItems: 'center',}}>
                                <Image style={{width: 30, height: 30}} source={require('../../../img/UserCenter/timg.png')} />
                            </View>
                        </TouchableOpacity>
                    </View>
                )}

                <View  style={{borderRadius:10,backgroundColor:'#ffffff',marginLeft:15,marginRight:15,paddingLeft:15,paddingRight:10}}>
                {
                    otherAddress.map((rowData, i,array) => (
                        <View  key={i} style={[{flexDirection: 'row',justifyContent: 'center',height:80,borderColor:'#F4F4F4',},
                            array.length!=(i+1) && {borderBottomWidth:1}
                        ]}>
                            <View style={{flex:5,flexDirection: 'column',justifyContent: 'center',}}>
                                <Text style={{color:'#4A4A4A',fontSize:16,}}>
                                    <Text>{rowData.province}</Text>
                                    <Text>{rowData.city}</Text>
                                    <Text>{rowData.area}</Text>
                                    <Text>{rowData.addressDetail}</Text>
                                </Text>
                            </View>
                            <TouchableOpacity onPress={()=>{this.modifyAddressPage(rowData)}} style={{justifyContent:'center',alignItems: 'center',flex:1}}>
                                <View style={{flex:1,justifyContent: 'center',alignItems: 'center',}}>
                                    <Image style={{width: 30, height: 30}} source={require('../../../img/UserCenter/timg.png')} />
                                </View>
                            </TouchableOpacity>
                        </View>
                    ))
                }
                </View>



                <View  style={{marginTop:20}}></View>
            </ScrollView>
        );
    }
}

const styles = StyleSheet.create({
    defaultAddress :{

    },

});

