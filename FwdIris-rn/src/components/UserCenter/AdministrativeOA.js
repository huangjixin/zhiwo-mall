import React from 'react';
import {
    Alert,
    StyleSheet,
    ScrollView,
    Text,
    View,
    Image,
    Button,
    ImageBackground,
    TouchableWithoutFeedback,
    DeviceEventEmitter
} from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";

export class AdministrativeOA extends React.Component {
    AdministrativeOAToOaApplyForm = () => {
        //alert('请假申请');
        DeviceEventEmitter.emit('AdministrativeOAToOaApplyForm'); //oa申请跳转到请假申请
    }
    AdministrativeOAToAddressChangeApply = () => {
        //alert('地址变更申请');
        DeviceEventEmitter.emit('AdministrativeOAToAddressChangeApply'); //oa申请跳转到地址变更申请
    }
    AdministrativeOAToMbilePhoneChangeApply = () => {
        //alert('手机变更申请');
        DeviceEventEmitter.emit('AdministrativeOAToMbilePhoneChangeApply'); //oa申请跳转到手机变更申请
    }
    AdministrativeOAToBankCardChangeApply = () => {
        //alert('银行卡变更申请');
        DeviceEventEmitter.emit('AdministrativeOAToBankCardChangeApply'); //oa申请跳转到银行卡变更申请
    }
    AdministrativeOAToPromotionApply = () => {
        //alert('晋级申请');
        DeviceEventEmitter.emit('AdministrativeOAToPromotionApply'); //oa申请跳转到晋级申请
    }
    AdministrativeOAToComplexEffectApply = () => {
        //alert('复效申请');
        DeviceEventEmitter.emit('AdministrativeOAToComplexEffectApply'); //oa申请跳转到复效申请
    }
    AdministrativeOAToQuiteApply = () => {
        //alert('离职申请');
        DeviceEventEmitter.emit('AdministrativeOAToQuiteApply'); //oa申请跳转到离职申请
    }
    AdministrativeOAToIncomeProof = () => {//oa申请跳转到工资证明
        // alert("123");
        DeviceEventEmitter.emit('IncomeProof');
    }

    AdministrativeOAToOtherProof = () => {//oa申请跳转到其他证明
        
    }

    AdministrativeOAToOnJobProof = () => {//oa申请跳转到在职证明
        // alert("123");
        DeviceEventEmitter.emit('JobProof'); //oa申请跳转到离职申请
        //alert('在职证明');
        // Alert.alert(
        //     "",
        //     "是否确认提交申请",
        //     [
        //         {text: '确定', onPress: this.userConfirmed},
        //         {text: '取消', onPress: this.userCanceled}
        //     ],
        //     {cancelable: true}
        // );
    }
    AdministrativeOAToOtherProof = () => {//oa申请跳转到其他证明
        // alert('其他证明');
        DeviceEventEmitter.emit('OtherProve');
    }

    AdministrativeOATollPowerful = () => {//oa申请跳转到万能申请
        //alert('万能申请');
        DeviceEventEmitter.emit('OtherApply');
    }

    render() {
        return (
            <ScrollView style={{marginBottom: 10, backgroundColor: '#FBFBFB'}}>
                {/* <View style={{backgroundColor:'#FFDD00',height:50,flexDirection: 'row',justifyContent:'flex-start',alignItems:'center',}}>
                    <View style={{flex:3.5,paddingLeft:10,}}>
                        <TouchableWithoutFeedback style={{width:30,height:30,}}  onPress={() => this.props.navigation.goBack()}>
                            <Image style={{width:11,height:22,}}source={require('../../../img/UserCenter/GoBackBlack.png')}/>
                        </TouchableWithoutFeedback>
                    </View>
                    <Text style={{flex:6.5,fontSize:22,}}>我的申请</Text>
                </View> */}
                <ApplyCommonHeader title={'我的申请'} onReturn={() => this.props.navigation.goBack()}/>

                <Text style={{fontSize: 15, paddingLeft: 20, paddingTop: 20, color: '#C3C3C3'}}>请假</Text>
                <View style={[styles.itemsView, {
                    backgroundColor: '#FFFFFF',
                    height: 110,
                    marginLeft: 15,
                    marginRight: 15,
                    marginTop: 15,
                    borderRadius: 15,
                }]}>
                    <View style={[styles.itemView, {flex: 3.33,}]}>
                        <TouchableWithoutFeedback onPress={this.AdministrativeOAToOaApplyForm}>
                            <Image style={styles.itemImage} source={require('../../../img/UserCenter/leave.png')}/>
                        </TouchableWithoutFeedback>
                        <Text style={styles.itemText}>请假</Text>
                    </View>
                    <View style={[styles.itemView, {flex: 3.33,}]}>
                    </View>
                    <View style={[styles.itemView, {flex: 3.33,}]}>
                    </View>
                </View>


                <Text style={{fontSize: 15, paddingLeft: 20, paddingTop: 20, color: '#C3C3C3'}}>信息变更 </Text>
                <View style={[styles.itemsView, {
                    backgroundColor: '#FFFFFF',
                    height: 110,
                    marginLeft: 15,
                    marginRight: 15,
                    marginTop: 15,
                    borderRadius: 15
                }]}>
                    <TouchableWithoutFeedback onPress={this.AdministrativeOAToAddressChangeApply}>
                        <View style={[styles.itemView, {flex: 3.33,}]}>
                            {/*<Image style={styles.itemImage} source={require('../../../img/UserCenter/address.gif')} />*/}
                            <Image style={{width: 60, height: 60,}}
                                   source={require('../../../img/UserCenter/address.gif')}/>
                            <Text style={styles.itemText}>地址</Text>
                        </View>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={this.AdministrativeOAToMbilePhoneChangeApply}>
                        <View style={[styles.itemView, {flex: 3.33,}]}>
                            {/*<Image style={styles.itemImage} source={require('../../../img/UserCenter/phone.gif')} />*/}
                            <Image style={{width: 60, height: 60,}}
                                   source={require('../../../img/UserCenter/phone.gif')}/>
                            <Text style={styles.itemText}>手机</Text>
                        </View>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={this.AdministrativeOAToBankCardChangeApply}>
                        <View style={[styles.itemView, {flex: 3.33,}]}>
                            {/*<Image style={styles.itemImage} source={require('../../../img/UserCenter/Bank.gif')} />*/}
                            <Image style={{width: 60, height: 60,}}
                                   source={require('../../../img/UserCenter/Bank.gif')}/>
                            <Text style={styles.itemText}>银行卡</Text>
                        </View>
                    </TouchableWithoutFeedback>
                </View>

                <Text style={{fontSize: 15, paddingLeft: 20, paddingTop: 20, color: '#C3C3C3'}}>人员变动 </Text>
                <View style={[styles.itemsView, {
                    backgroundColor: '#FFFFFF',
                    height: 110,
                    marginLeft: 15,
                    marginRight: 15,
                    marginTop: 15,
                    borderRadius: 15
                }]}>

                    <TouchableWithoutFeedback onPress={this.AdministrativeOAToPromotionApply}>
                        <View style={[styles.itemView, {flex: 3.33,}]}>
                            <Image style={styles.itemImage} source={require('../../../img/UserCenter/Promotion.png')}/>

                            <Text style={styles.itemText}>晋级申请</Text>
                        </View>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={this.AdministrativeOAToComplexEffectApply}>
                        <View style={[styles.itemView, {flex: 3.33,}]}>
                            <Image style={styles.itemImage} source={require('../../../img/UserCenter/Complex.png')}/>
                            <Text style={styles.itemText}>复效申请</Text>
                        </View>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={this.AdministrativeOAToQuiteApply}>
                        <View style={[styles.itemView, {flex: 3.33,}]}>
                            <Image style={styles.itemImage} source={require('../../../img/UserCenter/Quit.png')}/>
                            <Text style={styles.itemText}>离职申请</Text>
                        </View>
                    </TouchableWithoutFeedback>
                </View>


                <Text style={{fontSize: 15, paddingLeft: 20, paddingTop: 20, color: '#C3C3C3'}}>材料 </Text>
                <View style={[styles.itemsView, {
                    backgroundColor: '#FFFFFF',
                    height: 110,
                    marginLeft: 15,
                    marginRight: 15,
                    marginTop: 15,
                    borderRadius: 15
                }]}>
                    <TouchableWithoutFeedback onPress={this.AdministrativeOAToIncomeProof}>
                        <View style={[styles.itemView, {flex: 3.33,}]}>
                            <Image style={styles.itemImage} source={require('../../../img/UserCenter/wages.png')}/>
                            <Text style={styles.itemText}>收入证明</Text>
                        </View>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={this.AdministrativeOAToOnJobProof}>
                        <View style={[styles.itemView, {flex: 3.33,}]}>
                            <Image style={styles.itemImage} source={require('../../../img/UserCenter/job.png')}/>
                            <Text style={styles.itemText}>在职证明</Text>
                        </View>
                    </TouchableWithoutFeedback>
                    <TouchableWithoutFeedback onPress={this.AdministrativeOAToOtherProof}>
                        <View style={[styles.itemView, {flex: 3.33,}]}>
                            <Image style={styles.itemImage} source={require('../../../img/UserCenter/other.png')}/>
                            <Text style={styles.itemText}>其他证明</Text>
                        </View>
                    </TouchableWithoutFeedback>
                </View>

                <Text style={{fontSize: 15, paddingLeft: 20, paddingTop: 20, color: '#C3C3C3'}}>材料 </Text>
                <View style={[styles.itemsView, {
                    backgroundColor: '#FFFFFF',
                    height: 110,
                    marginLeft: 15,
                    marginRight: 15,
                    marginTop: 15,
                    borderRadius: 15
                }]}>
                    <TouchableWithoutFeedback onPress={this.AdministrativeOATollPowerful}>
                        <View style={[styles.itemView, {flex: 3.33,}]}>
                            <Image style={styles.itemImage}
                                   source={require('../../../img/UserCenter/allPowerful.png')}/>
                            <Text style={styles.itemText}>万能申请</Text>
                        </View>
                    </TouchableWithoutFeedback>
                    <View style={[styles.itemView, {flex: 3.33,}]}>
                    </View>
                    <View style={[styles.itemView, {flex: 3.33,}]}>
                    </View>
                </View>
                <View style={{height:30}}>
                    </View>
            </ScrollView>
        );
    }
}

const styles = StyleSheet.create({
    container: {flex: 1,},
    itemContentView: {
        borderColor: '#DCDCDC',
        borderWidth: 0.5,
        backgroundColor: 'white',
        marginTop: 20,
        marginLeft: 1,
        marginRight: 1,
        paddingBottom: 10
    },
    itemTitleView: {borderBottomColor: '#C3C3C3', borderBottomWidth: 0.5, margin: 10,},
    itemTitleText: {paddingBottom: 10, fontSize: 15,},
    itemsView: {flexDirection: 'row', justifyContent: 'space-around', alignItems: 'center',
    shadowColor:'black',shadowOpacity: 0.1,shadowRadius: StyleSheet.hairlineWidth,
    shadowOffset: {
        height: StyleSheet.hairlineWidth,
    },
    elevation: 2},
    itemView: {alignItems: 'center',},
    itemImage: {width: 40, height: 40,},
    itemText: {fontSize: 15, marginTop: 10,},
});

