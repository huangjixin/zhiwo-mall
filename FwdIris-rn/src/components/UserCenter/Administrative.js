import React from 'react';
import {Alert,StyleSheet, ScrollView, Text ,View,Image,ImageBackground,TouchableWithoutFeedback,DeviceEventEmitter,Dimensions,Platform} from 'react-native';
import {TabNavigator, TabBarTop, TabBarBottom} from 'react-navigation';
import {MyApplyToBeCheck} from './Administrative/MyApplyToBeCheck';
import {MyApplyToBeAudited} from './Administrative/MyApplyToBeAudited';
import {MyApprovalToBeCheck} from './Administrative/MyApprovalToBeCheck';
import {MyApprovalToBeAudited} from './Administrative/MyApprovalToBeAudited';


class MyApplication extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            selectedIndex:0,
        }
    }

    onExaminationAndApproval=()=>{
        //alert('审批通过');
        this.setState( {
            selectedIndex:0
        })
    }
    inExaminationAndApproval=()=>{
        //alert('审批中');
        this.setState( {
            selectedIndex:1
        })
    }
    render() {
        return (
            <View style={{backgroundColor:'#F8F8F8',}}>
                <View style={{flexDirection: 'row',justifyContent:'space-around',alignItems: 'center',marginTop:20,marginBottom:5,}} >
                    <TouchableWithoutFeedback onPress={this.onExaminationAndApproval }>
                        <Text style={{flex:4.5,textAlign:'center',marginLeft:20,fontSize:16,lineHeight:40,borderRadius:5,marginRight:10,backgroundColor:this.state.selectedIndex ==0?'#FFDD00':'#FFFFFF',color:this.state.selectedIndex ==0?'#120F00':'#BEBEBE',}}>待审批</Text>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={this.inExaminationAndApproval}>
                        <Text style={{flex:4.5,textAlign:'center',marginRight:20,fontSize:16,lineHeight:40,borderRadius:5,backgroundColor:this.state.selectedIndex ==1?'#FFDD00':'#FFFFFF',color:this.state.selectedIndex ==1?'#120F00':'#BEBEBE',}}>已审批</Text>
                    </TouchableWithoutFeedback>
                </View>

                {/*margintop 15*/}

                {this.state.selectedIndex==0?(
                   <MyApplyToBeCheck/>
                ):(
                    <MyApplyToBeAudited/>
                )}


                {/*<FlatList*/}
                    {/*data={this.state.myApplyToBeCheckList}*/}
                    {/*renderItem={({item}) =>*/}
                        {/*<View style={{backgroundColor:'white',borderRadius:5,*/}
                            {/*borderColor:'#CCCCCC',borderWidth:0.5,border:'sold',marginLeft:1,marginRight:1,marginTop:10,paddingBottom:5,}}>*/}
                            {/*<View style={{paddingTop:10,paddingBottom:10,flexDirection: 'row',borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:10,marginRight:10,}}>*/}
                                {/*<Text style={{fontSize:18,flex:8,}}>晋级申请</Text>*/}
                                {/*<Text style={{fontSize:18,flex:2,paddingLeft:50,color:'#FFCC33',fontWeight:'bold'}}>待审批</Text>*/}
                            {/*</View>*/}
                            {/*<View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:30,marginRight:10,}}>*/}
                                {/*<Text style={{fontSize:15,flex:8,}}>当前职级：LA</Text>*/}
                                {/*<Text style={{fontSize:15,flex:2,paddingLeft:50,}}>05/12</Text>*/}
                            {/*</View>*/}
                            {/*<View style={{marginBottom:10,flexDirection: 'row',marginLeft:30,marginRight:10,}}>*/}
                                {/*<Text style={{fontSize:15,flex:8,}}>晋升职级：SM</Text>*/}
                            {/*</View>*/}
                            {/*<View style={{flexDirection: 'row',marginLeft:30,marginRight:10,}}>*/}
                                {/*<Text style={{fontSize:15,flex:8,}}>说明：上季度超额完成所有指标</Text>*/}
                            {/*</View>*/}
                        {/*</View>*/}
                    {/*}*/}
                {/*/>*/}
            </View>


        );
    }
}
class MyApproval extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            selectedIndex:0,
        }
    }
    inExaminationAndApproval=()=>{
        //alert('待我审批');
        this.setState( {
            selectedIndex:0,
        })
    }
    onExaminationAndApproval=()=>{
        //alert('我已审批');
        this.setState( {
            selectedIndex:1,
        })
    }

    render() {
        return (


            <View style={{backgroundColor:'#F8F8F8',}}>
                <View style={{flexDirection: 'row',justifyContent:'space-around',alignItems: 'center',marginTop:20,marginBottom:5,}} >
                    <TouchableWithoutFeedback onPress={this.inExaminationAndApproval}>
                        <Text style={{flex:4.5,textAlign:'center',marginLeft:20,fontSize:16,lineHeight:40,borderRadius:5,marginRight:10,
                            backgroundColor:this.state.selectedIndex ==0?'#FFDD00':'#FFFFFF',color:this.state.selectedIndex ==0?'#120F00':'#BEBEBE',}}>待我审批</Text>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={this.onExaminationAndApproval}>
                        <Text style={{flex:4.5,textAlign:'center',marginRight:20,fontSize:16,lineHeight:40,borderRadius:5,
                            backgroundColor:this.state.selectedIndex ==1?'#FFDD00':'#FFFFFF',color:this.state.selectedIndex ==1?'#120F00':'#BEBEBE',}}>我已审批</Text>
                    </TouchableWithoutFeedback>
                </View>







                {this.state.selectedIndex==0?(
                    <MyApprovalToBeCheck/>
                ):(
                    <MyApprovalToBeAudited/>
                )}



                {/*
                <View style={{flexDirection: 'row',justifyContent:'space-around',backgroundColor: '#F2F2F2',alignItems: 'center',}} >
                    <TouchableWithoutFeedback onPress={this.onExaminationAndApproval }>
                        <Text style={{backgroundColor:'#F8F8F8',marginTop:5,marginBottom:5,borderWidth:0.5,
                            borderColor:'#CCCCCC',height:40,paddingTop:8,paddingLeft:20,paddingRight:20,fontSize:18}}>审批通过</Text>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={this.inExaminationAndApproval}>
                        <Text style={{backgroundColor:'#F8F8F8',marginTop:5,marginBottom:5,borderWidth:0.5,
                            borderColor:'#CCCCCC',height:40,paddingTop:8,paddingLeft:20,paddingRight:20,fontSize:18}}>审批中</Text>
                    </TouchableWithoutFeedback>
                </View>
                <FlatList
                    data={this.state.myApplyToBeCheckList}
                    renderItem={({item}) =>
                        <View style={{backgroundColor:'white',borderRadius:5,
                            borderColor:'#CCCCCC',borderWidth:0.5,border:'sold',marginLeft:1,marginRight:1,marginTop:10,paddingBottom:5,}}>
                            <View style={{paddingTop:10,paddingBottom:10,flexDirection: 'row',borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:10,marginRight:10,}}>
                                <Text style={{fontSize:18,flex:8,}}>晋级申请</Text>
                                <Text style={{fontSize:18,flex:2,paddingLeft:50,color:'#FFCC33',fontWeight:'bold'}}>待审批</Text>
                            </View>
                            <View style={{paddingTop:10,marginBottom:10,flexDirection: 'row',marginLeft:30,marginRight:10,}}>
                                <Text style={{fontSize:15,flex:8,}}>当前职级：LA</Text>
                                <Text style={{fontSize:15,flex:2,paddingLeft:50,}}>05/12</Text>
                            </View>
                            <View style={{marginBottom:10,flexDirection: 'row',marginLeft:30,marginRight:10,}}>
                                <Text style={{fontSize:15,flex:8,}}>晋升职级：SM</Text>
                            </View>
                            <View style={{flexDirection: 'row',marginLeft:30,marginRight:10,}}>
                                <Text style={{fontSize:15,flex:8,}}>说明：上季度超额完成所有指标</Text>
                            </View>
                        </View>
                    }
                />

                */}




            </View>


        );
    }
}
let screenWidth = Dimensions.get('window').width/6;
let paddingLeft = (Dimensions.get('window').width/2-screenWidth)/2;
let screenHeight = Dimensions.get('window').height-30;
const AdministrativeTabr = TabNavigator({
    MyApplication: {
            screen: MyApplication,
            navigationOptions: {
                tabBarLabel: '我的申请',
            }
        },
    MyApproval: {
            screen: MyApproval,
            navigationOptions: {
                tabBarLabel: '我的审批',
            }
        },
    },{
        tabBarOptions: {
            activeTintColor: '#000000',
            inactiveTintColor: '#AB9300',
            activeBackgroundColor: '#FFDD00',
            inactiveBackgroundColor: '#FFDD00',
            labelStyle: {
                textAlign:'center',
                fontSize: 18, // 文字大小
                //paddingVertical:6,
                // borderBottomWidth:2,
                // borderBottomColor:'#000000',
                // paddingTop:10,
                // paddingBottom:10,
            },
            // tabStyle: {
            //     //  backgroundColor: '#FFDD00',
            //     //  textAlign:'center',
            // },

            style: {
                // height:80,
                textAlign:'center',
                // paddingTop:-5,
                // paddingLeft:60,
                // paddingRight:60,
                // borderBottomWidth:3,
                // borderBottomColor:'#000000',
                // paddingBottom:25,
                backgroundColor: '#FFDD00', // TabBar 背景色
                // borderTopWidth:0,
                //height: 44,
                // width : 200,
                //lineHeight: 44,
            },
            indicatorStyle: {
                // position:'relative',
                //paddingLeft:0,
                left:paddingLeft,
                height: 3,
                width:screenWidth,
                backgroundColor: '#000000'
            },
        },
        // tabBarComponent: TabBarTop,
        tabBarPosition: 'top',
        animationEnabled: false,
        swipeEnabled: false,
        lazy:true,
        backBehavior:'none'
    }
);

export class Administrative extends React.Component {
    constructor(props){
        super(props);
        let screenWidth = Dimensions.get('window').width/2;
        let addImagePaddingLeft = screenWidth - 40;
        this.state = {
            addImagePaddingLeft:screenWidth
        }
    }

    administrativeToOA=()=>{
        //alert('aaaaa');
        DeviceEventEmitter.emit('administrativeToOA'); //我的申请-》OA申请
    }

    _onLayout=(event)=>{  
        let screenWidth = Dimensions.get('window').width/2;
        let addImagePaddingLeft = screenWidth - 60;
        this.setState({addImagePaddingLeft:addImagePaddingLeft});
    }  
        
    render() {
        return (

            <View style={{paddingBottom:10}} onLayout={this._onLayout}>
                <View style={{height:screenHeight,}}>
                    <View style={{height:Platform.OS === "ios" ?24 :0,backgroundColor:'#FFDD00',}}></View>
                    <AdministrativeTabr style={{fontSize:22,backgroundColor:'#FFDD00'}}/>
                    <View style={{width:20,backgroundColor:'#FFDD00',position:'absolute',left:15,top:Platform.OS === "ios" ?44:20,}}>
                        <TouchableWithoutFeedback style={{width:30,height:30,}}  onPress={() => this.props.navigation.goBack()}>
                            <Image style={{width:11,height:22,}} source={require('../../../img/UserCenter/GoBackBlack.png')}/>
                        </TouchableWithoutFeedback>
                    </View>
                </View>
                <TouchableWithoutFeedback onPress={this.administrativeToOA}>
                    <View style={{position:'absolute',left:this.state.addImagePaddingLeft,bottom:15}}>
                        <Image style={{width:120,height:120,}} source={require('../../../img/UserCenter/add.png')}/>
                    </View>
                </TouchableWithoutFeedback>

            </View>


           // <View style={{paddingBottom:10}} onLayout={this._onLayout}>
            //  <ScrollView>
            //    <AdministrativeTabr style={{fontSize:22,backgroundColor:'#FFDD00'}}/>
            //     <View style={{width:20,backgroundColor:'#FFDD00',position:'absolute',left:15,top:20,}}>
            //       <TouchableWithoutFeedback style={{width:30,height:30,}}  onPress={() => this.props.navigation.goBack()}>
            //            <Image style={{width:11,height:22,}} source={require('../../../img/UserCenter/GoBackBlack.png')}/>
            //         </TouchableWithoutFeedback>
            //      </View>
            //   </ScrollView>
            //   <TouchableWithoutFeedback onPress={this.administrativeToOA}>
            //     <View style={{position:'absolute',left:this.state.addImagePaddingLeft,bottom:0}}>
            //        <Image style={{width:120,height:120,}} source={require('../../../img/UserCenter/add.png')}/>
            //    </View>
            //  </TouchableWithoutFeedback>

            //  </View>

        );
    }
}
const styles = StyleSheet.create({
});