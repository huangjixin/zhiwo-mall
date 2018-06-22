import React from 'react';
import {StyleSheet,Dimensions, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,ImageBackground,DeviceEventEmitter,TouchableOpacity,FlatList} from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";


var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class BankCardChangeApply extends React.Component {

    requestData = [
        {
            id:1,
            description:"招行存储卡",
            cardType:1,
            isDefault:true,
            cardNo:"****  ****  ****  4579",
        },{
            id:2,
            description:"交通存储卡",
            cardType:2,
            isDefault:false,
            cardNo:"****  ****  ****  4579",
        },{
            id:3,
            description:"工行存储卡",
            cardType:3,
            isDefault:false,
            cardNo:"****  ****  ****  4579",
        }
    ]
    ;

    constructor(props){
        super(props);
        this.state = {
            data:[],
        }
    }
    componentWillMount() {
        this.fetchData();
    }

    getCardStyle = (cardType) =>{
        let ret = null;
        switch (cardType) {
            case 1 :
                ret = {
                    icon: require('../../../img/UserCenter/CMB.gif'),
                    backgroundColor: {backgroundColor: '#FFDD00'}
                }
                break;
            case 2 :
                ret = {
                    icon: require('../../../img/UserCenter/BOCOM.gif'),
                    backgroundColor: {backgroundColor: '#37B5FF'}
                }
                break;
            case 3 :
                ret = {
                    icon: require('../../../img/UserCenter/ICBC.gif'),
                    backgroundColor: {backgroundColor: '#FF8090'}
                }
                break;
            default :
                ret = {
                    icon: require('../../../img/UserCenter/CMB.gif'),
                    backgroundColor: {backgroundColor: '#FFDD00'}
                };
                break;
        }
        return ret;
    }
    // 网络请求
    fetchData() {
        // fetch('https://facebook.github.io/react-native/movies.json', {
        // })
        //     .then((response) => response.json())
        //     .then((responseData) => {
        //         let data = this.state.dataSource.concat(responseData.movies);
        //         let newData = [];
        //         for (var i = 0; i < data.length-3; i++) {
        //             newData.push(data[i]);
        //         }
        //         this.setState( {
        //             dataSource:data,
        //             newData:newData,
        //         })
        //     }).done();

        const data = this.requestData.map((rowData) => {
            const cradStyle = this.getCardStyle(rowData.cardType);
            return {...rowData,...cradStyle};
        });

        this.setState({
            data :data
        });

    }

    AddBankCardPage=()=>{
        DeviceEventEmitter.emit('AddBankCardPage'); //银行变更申请-》添加银行页面
    }

    BankCardReleaseBindingDetailsPage=(bankDetail)=>{
        if(!bankDetail.isDefault)
            DeviceEventEmitter.emit('BankCardReleaseBindingDetailsPage'); //银行变更申请-》银行详情页面
    }

    render() {
        const {data} = this.state;
        return (
            <ScrollView style={{backgroundColor:'#FFFFFF'}}>
                <View>

                    <ApplyCommonHeader title={'变更银行卡'} onReturn={() => this.props.navigation.goBack()}/>
                    {data.map((rowData, i,array) => (
                        <TouchableOpacity onPress={()=>{this.BankCardReleaseBindingDetailsPage(rowData)}}>
                            <View style={[rowData.backgroundColor,{height:100,marginTop:20,marginLeft:20,marginRight:20,borderRadius:10,
                                shadowColor:'#FFDD00',shadowOpacity: 0.1,shadowRadius: StyleSheet.hairlineWidth,
                                shadowOffset: {
                                    width: StyleSheet.hairlineWidth,
                                    height: StyleSheet.hairlineWidth,
                                },elevation: 4}]}>

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
                                    <Text style={{fontSize:20,color:'#000000',fontWeight:'300',fontSize:16}}>{rowData.cardNo}</Text>
                                </View>
                            </View>
                        </TouchableOpacity>
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

