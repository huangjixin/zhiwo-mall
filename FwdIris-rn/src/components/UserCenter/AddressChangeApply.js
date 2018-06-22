import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,DeviceEventEmitter,FlatList,Dimensions,TouchableOpacity} from 'react-native';
import CheckBox from 'react-native-checkbox';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class AddressChangeApply extends React.Component {

    requestData = [{
        id:1,
        isDefault:true,
        country:"中国",
        province:"",
        city:"上海市",
        area:"松江区",
        street:"古楼路",
        addressDetail:"古楼路1899弄8号楼902室",
    },{
        id:2,
        isDefault:false,
        country:"中国",
        province:"江苏省",
        city:"无锡市",
        area:"惠山区",
        street:"中山路",
        addressDetail:"中山路500号",
    },{
        id:3,
        isDefault:false,
        country:"中国",
        province:"江苏省",
        city:"无锡市",
        area:"惠山区",
        street:"中山路",
        addressDetail:"中山路501号",
    }
    ];


    constructor(props){
        super(props);
        this.state = {
            defaultAddress:[],
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


    // 网络请求
    fetchData() {
        // fetch('https://facebook.github.io/react-native/movies.json', {
        // })
        //     .then((response) => response.json())
        //     .then((responseData) => {
        //         let data = this.state.dataSource.concat(responseData.movies);
        //         let newData = [];
        //         for (var i = 0; i < data.length-2; i++) {
        //             newData.push(data[i]);
        //         }
        //         this.setState( {
        //             dataSource:data,
        //             newData:newData,
        //         })
        //     }).done();

        const defaultAddress = this.requestData.filter(item=>{
            return item.isDefault;
        });

        const otherAddress = this.requestData.filter(item=>{
            return !item.isDefault;
        });

        this.setState({
            defaultAddress:defaultAddress[0],
            otherAddress:otherAddress,
        });
    }

    render() {

        const {defaultAddress,otherAddress} = this.state;

        return (
            <ScrollView style={{backgroundColor:'#FBFBFB',}}>
                <ApplyCommonHeader
                    title={'我的地址'}
                    onReturn={() => this.props.navigation.goBack()}
                    actionName={'新增地址'}
                    onAction={() => this.AddAddressPage()}
                />
                <View  style={{marginTop:20}}></View>

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
                    <TouchableOpacity onPress={this.AddAddressPage} style={{justifyContent:'center',alignItems: 'center',flex:1}}>
                        <View style={{flex:1,justifyContent: 'center',alignItems: 'center',}}>
                            <Image style={{width: 30, height: 30}} source={require('../../../img/UserCenter/timg.png')} />
                        </View>
                    </TouchableOpacity>
                </View>

                <View  style={{borderRadius:10,backgroundColor:'#ffffff',marginLeft:15,marginRight:15,paddingLeft:15,paddingRight:10}}>
                {
                    otherAddress.map((rowData, i,array) => (
                        <View  style={[{flexDirection: 'row',justifyContent: 'center',height:80,borderColor:'#F4F4F4',},
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
                            <TouchableOpacity onPress={this.AddAddressPage} style={{justifyContent:'center',alignItems: 'center',flex:1}}>
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

