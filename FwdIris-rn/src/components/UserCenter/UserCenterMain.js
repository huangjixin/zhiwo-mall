import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TouchableWithoutFeedback,DeviceEventEmitter,Alert,Dimensions} from 'react-native';

var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
/*
class BottomView extends React.Component {
    render() {
        let rowImage = this.props.showRowImage == 'true'? (
            <Image style={{width:100,height:3}} source={require('../../img/UserCenter/RowLine.png')} />
        ) : (null);
        let columnImage = this.props.showColumnImage =='true'? (
            <Image source={require('../../img/UserCenter/ColumnLine.png')}
                   style={{width:3,height:100}}/>
        ) : (null);//Alerts
        //var i = this.props.imageName != null ? this.props.imageName : null;
        //let imagen = '../../img/UserCenter/'+{this.props.imageName}+'.png';
        return (
            <View style={{flexDirection: 'row'}}>
                <View  style={{width:100,}}>
                    <Image source={require('../../img/UserCenter/Alerts.png')}
                           style={{width:50,height:50,marginLeft:15,marginRight:15,marginTop:15,}}/>
                    <Text style={{marginLeft:15,marginRight:15,marginBottom:15,}}>{this.props.name}</Text>
                    {rowImage}
                </View>
                {columnImage}
            </View>
        );
    }
}*/
export class UserCenterMain extends React.Component {
    userCenterToElectronicBusiness=() =>{
        DeviceEventEmitter.emit('userCenterToElectronicBusiness'); //用户中心跳转到电子名片
}
    userCenterToAdministrative=()=>{
        //alert('行政审批');
        DeviceEventEmitter.emit('userCenterToAdministrative'); //用户中心跳转到行政审批
        // Alert.alert(
        //     "弹出框标题提示语",
        //     "弹出框正文提示语",
        //     [
        //         {text: '取消', onPress: this.userCanceled},
        //         {text: '确定', onPress: this.userConfirmed}
        //     ],
        //     {cancelable: true}
        // );
    }
    userCenterToMyAttendance=()=>{
        //alert('我的考勤');
    }
    userCenterToCustomerService=()=>{
        //alert('专属客服');
    }
    userCenterToOnlineLearning=()=>{
        //alert('在线学习');
        DeviceEventEmitter.emit('userCenterToOnlineLearning'); //用户中心跳转到在线学习
    }
    userCenterToAlerts=()=>{
        //alert('消息通知');
        DeviceEventEmitter.emit('userCenterToAlerts'); //用户中心跳转到消息通知
    }
    userCenterToMoreService=()=>{
        //alert('更多服务');
    }
    userCenterToAdministrativeOA=()=>{
        //alert('我的申请');
        DeviceEventEmitter.emit('administrativeToOA'); //用户中心跳转到我的申请
    }
    render() {
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <View style={{flexDirection: 'row',justifyContent:'flex-start',height:200,backgroundColor:'#FFDD00',}}>
                    <View style={{flex:6,paddingTop:40,paddingLeft:30}}>
                        <Image  source={require('../../../img/UserCenter/UserImage.jpeg')}
                                style={{width: 70, height: 70,borderRadius:35}} />
                        <Text style={{fontSize:20,color:'#000000',paddingTop:10}}>李俊芮</Text>
                        <Text style={{paddingTop:10}}>资深业务主管  |  广州  建业营管处</Text>
                    </View>
                    <View style={{flex:4,flexDirection: 'column',justifyContent: 'center'}}>
                        <View style={{flexDirection: 'row',justifyContent: 'center'}}>
                                <View style={{borderStyle: 'solid',borderWidth: 3,borderColor: '#FFEA67',width:110,height:110,borderRadius:110}}>
                                <Text style={{fontSize:40,width:110,textAlign:'center',marginTop:10,fontWeight:'400',color:'#000000'}}>20</Text>
                                <Text style={{width:110,textAlign:'center',color:'#C5AA00'}}>当前积分</Text>
                            </View>
                        </View>
                    </View>
                </View>
                <View style={{flexDirection: 'row',justifyContent:'space-around',backgroundColor:'#FFFFFF',height:110,alignItems: 'center',borderBottomWidth:0.5,borderBottomColor:'#f4f4f4',}}>
                    <View style={{flex:2.5,alignItems: 'center',}}>
                        <TouchableWithoutFeedback onPress={this.userCenterToAdministrative}>
                            <Image style={{width: 50, height: 50}} source={require('../../../img/UserCenter/Administrative.png')} />
                        </TouchableWithoutFeedback>
                        <Text style={{fontSize:15,marginTop:10,}}>审批</Text>
                    </View>
                    {/*<View style={{flex:2.5,alignItems: 'center'}}>*/}
                        {/*<TouchableWithoutFeedback onPress={this.userCenterToAdministrativeOA}>*/}
                            {/*<Image style={{width: 50, height: 50}} source={require('../../../img/UserCenter/ApplyFor.png')} />*/}
                        {/*</TouchableWithoutFeedback>*/}
                        {/*<Text style={{fontSize:15,marginTop:10,}}>申请</Text>*/}
                    {/*</View>*/}
                    <View style={{flex:2.5,alignItems: 'center'}}>
                        <TouchableWithoutFeedback onPress={this.userCenterToMyAttendance}>
                            <Image style={{width: 50, height: 50}} source={require('../../../img/UserCenter/MyAttendance.png')} />
                        </TouchableWithoutFeedback>
                        <Text style={{fontSize:15,marginTop:10,}}>考勤</Text>
                    </View>
                    <View style={{flex:2.5,alignItems: 'center'}}>
                        <TouchableWithoutFeedback onPress={this.userCenterToCustomerService}>
                            <Image style={{width: 50, height: 50}} source={require('../../../img/UserCenter/CustomerService.png')} />
                        </TouchableWithoutFeedback>
                        <Text style={{fontSize:15,marginTop:10,}}>客服</Text>
                    </View>
                </View>
                <View  style={{marginTop:10,backgroundColor:'#FFFFFF',height:60,borderBottomWidth:0.5,borderBottomColor:'#F4F4F4',}}>
                    <TouchableWithoutFeedback onPress={this.userCenterToElectronicBusiness}>
                        <View style={{flexDirection: 'row',justifyContent:'space-around'}}>
                            <View  style={{flex:1.5,flexDirection: 'row',justifyContent:'flex-end'}}>
                                <View  style={{flexDirection: 'column',justifyContent: 'center'}}>
                                    <Image style={{width: 25, height: 22,marginRight:10,}} source={require('../../../img/UserCenter/ElectronicBusinessCard.png')} />
                                </View>
                            </View>
                            <Text style={{flex:7,height:60,lineHeight:60,fontSize:18,paddingLeft:10,color:'#000000'}}>电子名片</Text>
                            <View  style={{flex:1.5,alignItems:'center',justifyContent:'center',}}>
                                <Image style={{width: 10, height: 16,marginRight:20,}} source={require('../../../img/UserCenter/rightArr.png')} />
                            </View>
                        </View>
                </TouchableWithoutFeedback>
                </View>
                <View  style={{marginTop:1,backgroundColor:'#FFFFFF',height:60,borderBottomWidth:0.5,borderBottomColor:'#F4F4F4',}}>
                    <TouchableWithoutFeedback onPress={this.userCenterToOnlineLearning}>
                        <View style={{flexDirection: 'row',justifyContent:'space-around'}}>
                            <View  style={{flex:1.5,flexDirection: 'row',justifyContent:'flex-end'}}>
                                <View  style={{flexDirection: 'column',justifyContent: 'center'}}>
                                    <Image style={{width: 25, height: 22,marginRight:10,}} source={require('../../../img/UserCenter/OnlineLearning.png')} />
                                </View>
                            </View>
                            <Text style={{flex:7,height:60,lineHeight:60,fontSize:18,paddingLeft:10,color:'#000000'}}>在线学习</Text>
                            <View  style={{flex:1.5,alignItems:'center',justifyContent:'center',}}>
                                <Image style={{width: 10, height: 16,marginRight:20,}} source={require('../../../img/UserCenter/rightArr.png')} />
                            </View>
                        </View>
                    </TouchableWithoutFeedback>
                </View>
                <View  style={{marginTop:1,backgroundColor:'#FFFFFF',height:60,borderBottomWidth:0.5,borderBottomColor:'#F4F4F4',}}>
                    <TouchableWithoutFeedback onPress={this.userCenterToAlerts}>
                        <View style={{flexDirection: 'row',justifyContent:'space-around'}}>
                            <View  style={{flex:1.5,flexDirection: 'row',justifyContent:'flex-end'}}>
                                <View  style={{flexDirection: 'column',justifyContent: 'center'}}>
                                    <Image style={{width: 25, height: 22,marginRight:10,}} source={require('../../../img/UserCenter/Alerts.png')} />
                                </View>
                            </View>
                            <Text style={{flex:7,height:60,lineHeight:60,fontSize:18,paddingLeft:10,color:'#000000'}}>消息通知</Text>
                            <View  style={{flex:1.5,alignItems:'center',justifyContent:'center',}}>
                                <Image style={{width: 10, height: 16,marginRight:20,}} source={require('../../../img/UserCenter/rightArr.png')} />
                            </View>
                            {/*<Text style={{flex:1.5,height:60,lineHeight:60,fontSize:40,flexDirection: 'row',justifyContent:'flex-end',color:'#ECECEC'}}>></Text>*/}
                        </View>
                    </TouchableWithoutFeedback>
                </View>
                <TouchableWithoutFeedback onPress={this.userCenterToMoreService}>
                    <View style={{position: 'relative',marginLeft:'auto',marginRight:'auto',bottom:0,marginTop:30,marginBottom:80,
                        borderRadius:50,backgroundColor:'#FFDD00',width:60,height:60,justifyContent:'center',
                        alignItems:'center',}}>
                        <Image style={{width:20,height:20,}} source={require('../../../img/UserCenter/MoreService.png')}/>
                    </View>
                </TouchableWithoutFeedback>


















                <View style={styles.totalView}>
                    {/*<View style={styles.topView}>*/}
                        {/*<View style={{marginLeft:15,marginTop:15,marginRight:20,}}>*/}
                            {/*<Image  source={require('../../../img/UserCenter/UserImage.jpeg')}*/}
                                    {/*style={{width: 70, height: 70,borderRadius:35}} />*/}
                        {/*</View>*/}
                        {/*<View style={{alignSelf: 'flex-end',marginBottom:20,}}>*/}
                            {/*<Text style={styles.topText}>七月流火</Text>*/}
                            {/*<View style={styles.topScoreView}>*/}
                                {/*<Text style={{color:'white',fontWeight:'bold',paddingTop:10}}>当前积分   </Text>*/}
                                {/*<Text style={{color:'white',fontWeight:'bold',paddingTop:10}}>20</Text>*/}
                            {/*</View>*/}
                        {/*</View>*/}
                    {/*</View>*/}
                    {/* <View style={styles.bottomView}><Text>  | 我的应用</Text>
                        <View style={styles.bottomTotalView}>

                            <View style={{flexDirection: 'row',justifyContent: 'center'}}>

                                <TouchableWithoutFeedback onPress={this.userCenterToElectronicBusiness}>
                                    <View  style={{width:100,}}>
                                        <Image source={require('../../../img/UserCenter/ElectronicBusinessCard.png')}
                                               style={{width:50,height:50,marginLeft:30,marginRight:20,marginTop:20,}}/>
                                        <Text style={{marginLeft:25,marginRight:15,marginBottom:15,fontSize:14,marginTop:10,}}>电子名片</Text>
                                        <Image style={{width:100,height:3}} source={require('../../../img/UserCenter/RowLine.png')} />
                                    </View>
                                </TouchableWithoutFeedback>
                                <Image source={require('../../../img/UserCenter/ColumnLine.png')}
                                       style={{width:3,height:115}}/>

                                <TouchableWithoutFeedback onPress={this.userCenterToAdministrative}>
                                    <View  style={{width:100,}}>
                                        <Image source={require('../../../img/UserCenter/Administrative.png')}
                                               style={{width:50,height:50,marginLeft:30,marginRight:20,marginTop:20,}}/>
                                        <Text style={{marginLeft:25,marginRight:15,marginBottom:15,fontSize:14,marginTop:10,}}>行政审批</Text>
                                        <Image style={{width:100,height:3}} source={require('../../../img/UserCenter/RowLine.png')} />
                                    </View>
                                </TouchableWithoutFeedback>
                                <Image source={require('../../../img/UserCenter/ColumnLine.png')}
                                       style={{width:3,height:115}}/>

                                <TouchableWithoutFeedback onPress={this.userCenterToMyAttendance}>
                                    <View  style={{width:100,}}>
                                        <Image source={require('../../../img/UserCenter/MyAttendance.png')}
                                               style={{width:50,height:50,marginLeft:30,marginRight:20,marginTop:20,}}/>
                                        <Text style={{marginLeft:25,marginRight:15,marginBottom:15,fontSize:14,marginTop:10,}}>我的考勤</Text>
                                        <Image style={{width:100,height:3}} source={require('../../../img/UserCenter/RowLine.png')} />
                                    </View>
                                </TouchableWithoutFeedback>
                                <Image source={require('../../../img/UserCenter/ColumnLine.png')}
                                       style={{width:0,height:115}}/>

                            </View>

                            <View style={{flexDirection:'row',justifyContent: 'center'}}>

                                <TouchableWithoutFeedback onPress={this.userCenterToCustomerService}>
                                    <View  style={{width:100,}}>
                                        <Image source={require('../../../img/UserCenter/CustomerService.png')}
                                               style={{width:50,height:50,marginLeft:30,marginRight:20,marginTop:20,}}/>
                                        <Text style={{marginLeft:25,marginRight:15,marginBottom:15,fontSize:14,marginTop:10,}}>专属客服</Text>
                                        <Image style={{width:100,height:3}} source={require('../../../img/UserCenter/RowLine.png')} />
                                    </View>
                                </TouchableWithoutFeedback>
                                <Image source={require('../../../img/UserCenter/ColumnLine.png')}
                                       style={{width:3,height:115}}/>

                                <TouchableWithoutFeedback onPress={this.userCenterToOnlineLearning}>
                                    <View  style={{width:100,}}>
                                        <Image source={require('../../../img/UserCenter/OnlineLearning.png')}
                                               style={{width:50,height:50,marginLeft:30,marginRight:20,marginTop:20,}}/>
                                        <Text style={{marginLeft:25,marginRight:15,marginBottom:15,fontSize:14,marginTop:10,}}>在线学习</Text>
                                        <Image style={{width:100,height:3}} source={require('../../../img/UserCenter/RowLine.png')} />
                                    </View>
                                </TouchableWithoutFeedback>
                                <Image source={require('../../../img/UserCenter/ColumnLine.png')}
                                       style={{width:3,height:115}}/>

                                <TouchableWithoutFeedback onPress={this.userCenterToAlerts}>
                                    <View  style={{width:100,}}>
                                        <Image source={require('../../../img/UserCenter/Alerts.png')}
                                               style={{width:50,height:50,marginLeft:30,marginRight:20,marginTop:20,}}/>
                                        <Text style={{marginLeft:25,marginRight:15,marginBottom:15,fontSize:14,marginTop:10,}}>消息通知</Text>
                                        <Image style={{width:100,height:3}} source={require('../../../img/UserCenter/RowLine.png')} />
                                    </View>
                                </TouchableWithoutFeedback>
                                <Image source={require('../../../img/UserCenter/ColumnLine.png')}
                                       style={{width:0,height:115}}/>
                            </View>

                            <View style={{flexDirection:'row',justifyContent: 'center'}}>

                                <TouchableWithoutFeedback onPress={this.userCenterToMoreService}>
                                    <View  style={{width:100,}}>
                                        <Image source={require('../../../img/UserCenter/MoreService.png')}
                                               style={{width:50,height:50,marginLeft:30,marginRight:20,marginTop:20,}}/>
                                        <Text style={{marginLeft:25,marginRight:15,marginBottom:15,fontSize:14,marginTop:10,}}>更多服务</Text>
                                        <Image style={{width:100,height:0}} source={require('../../../img/UserCenter/RowLine.png')} />
                                    </View>
                                </TouchableWithoutFeedback>
                                <Image source={require('../../../img/UserCenter/ColumnLine.png')}
                                       style={{width:3,height:115}}/>

                                <View  style={{width:100,}}>
                                    <Image
                                        style={{width:50,height:50,marginLeft:30,marginRight:20,marginTop:20,}}/>
                                    <Text style={{marginLeft:25,marginRight:15,marginBottom:15,fontSize:14,marginTop:10,}}></Text>
                                    <Image style={{width:100,height:0}} source={require('../../../img/UserCenter/RowLine.png')} />
                                </View>
                                <Image source={require('../../../img/UserCenter/ColumnLine.png')}
                                       style={{width:3,height:115}}/>

                                <View  style={{width:100,}}>
                                    <Image
                                        style={{width:50,height:50,marginLeft:30,marginRight:20,marginTop:20,}}/>
                                    <Text style={{marginLeft:25,marginRight:15,marginBottom:15,fontSize:14,marginTop:10,}}></Text>
                                    <Image style={{width:100,height:0}} source={require('../../../img/UserCenter/RowLine.png')} />
                                </View>
                                <Image source={require('../../../img/UserCenter/ColumnLine.png')}
                                       style={{width:0,height:115}}/>

                            </View>
                        </View>
                    </View>*/}
                </View>
            </ScrollView>

            /*
            <View style={{flexDirection: 'row',}}>
                <BottomView name="电子名片" showRowImage="true" showColumnImage="true" imageName="../../img/UserCenter/Alerts.png"/>
                <BottomView name="行政审批" showRowImage="true" showColumnImage="true"/>
                <BottomView name="我的考勤" showRowImage="true" showColumnImage="false"/>
            </View>
            <View style={{flexDirection: 'row',}}>
                <BottomView name="专属客服" showRowImage="true" showColumnImage="true"/>
                <BottomView name="在线学习" showRowImage="true" showColumnImage="true"/>
                <BottomView name="消息通知" showRowImage="true" showColumnImage="false"/>
            </View>
            <View style={{flexDirection: 'row',}}>
                <BottomView name="更多服务" showRowImage="false" showColumnImage="true"/>
                <BottomView showRowImage="false" showColumnImage="true"/>
                <BottomView showRowImage="false" showColumnImage="false"/>
            </View>*/

        );
    }
}

const styles = StyleSheet.create({
    container: {flex: 1,},
    totalView:{backgroundColor:'white', flex: 1,},
    topView:{flexDirection: 'row',backgroundColor:'rgba(153, 153, 153, 0.4)',height:100, },
    topImgView:{borderRadius:50,backgroundColor:'white',marginLeft:50,marginRight:20, },
    topScoreView:{flexDirection: 'row',color:'white',},
    topText:{color:'white',fontWeight:'bold'},
    bottomView:{marginTop:20},
    bottomTotalView:{flexDirection: 'column',justifyContent: 'center',alignItems: 'center',},
});


