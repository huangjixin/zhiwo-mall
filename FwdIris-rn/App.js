import React from 'react';
import {Text, View, Image, StyleSheet, Button, DeviceEventEmitter,BackHandler,Alert,} from 'react-native';
import { TabNavigator, TabBarBottom ,StackNavigator } from 'react-navigation';

import {NewsDetail} from './src/screens/NewsDetail';
import {MainScreen} from "./src/screens/MainScreen";
import {Router} from "./src/common/Router";
import {AddVisitCustomer} from "./src/components/Customer/AddVisitCustomer";
import {ElectronicBusinessCard} from "./src/components/UserCenter/ElectronicBusinessCard";
import {MyLearning}from'./src/components/UserCenter/MyLearning';
import {Administrative}from'./src/components/UserCenter/Administrative';
import {MyNew}from'./src/components/UserCenter/MyNew';
import {AdministrativeOA}from'./src/components/UserCenter/AdministrativeOA';
import {CustomerDetails} from "./src/components/Customer/CustomerDetails";
import {OaApplyForm}from'./src/components/UserCenter/OaApplyForm';
import {AddressChangeApply}from'./src/components/UserCenter/AddressChangeApply';
import {MobilePhoneChangeApply}from'./src/components/UserCenter/MobilePhoneChangeApply';
import {BankCardChangeApply}from'./src/components/UserCenter/BankCardChangeApply';
import {PromotionApply}from'./src/components/UserCenter/PromotionApply';
import {ComplexEffectApply}from'./src/components/UserCenter/ComplexEffectApply';
import {QuitApply}from'./src/components/UserCenter/QuitApply';
import {AddAddress}from'./src/components/UserCenter/AddAddress';
import {AddBankCard}from'./src/components/UserCenter/AddBankCard';
import {BankCardBindingDetails}from'./src/components/UserCenter/BankCardBindingDetails';
import {BankCardReleaseBindingDetails}from'./src/components/UserCenter/BankCardReleaseBindingDetails';
import {IncomeProof} from "./src/components/UserCenter/IncomeProof";
import { JobProof } from './src/components/UserCenter/apply/JobProof';
import {OtherApply} from "./src/components/UserCenter/OtherApply";
import {OtherProve} from "./src/components/UserCenter/OtherProve";
import { NewMobilePhoneChangeApply } from './src/components/UserCenter/NewMobilePhoneChangeApply';
import { AddAddressPhone } from './src/components/UserCenter/AddAddressPhone';
import { PromotionNotice } from './src/components/UserCenter/PromotionNotice';
import { PromotionNoticeAutograph } from './src/components/UserCenter/PromotionNoticeAutograph';
import {OaApplyFormDetail} from "./src/components/UserCenter/OaApplyFormDetail";



//其他页面添加在这即可注册并跳转
// const otherScreens = {
//     NewsDetailScreen: { screen: NewsDetail},
// }

// class MainScreenNavigator extends React.Component{
//     componentDidMount() {
//         let router = new Router(this.props.navigation);
//         const routeNames = Object.keys(otherScreens);
//         this.listener=routeNames.forEach(
//             routeName=>router.registerPageToRouter(routeName)
//         );
//     }
//     componentWillUnmount(){
//         this.listener.remove();
//     }
//     render() {
//         return (
//             <MainScreen/>
//         )
//     }
// }

// const mainScreens = {MainScreen: { screen: MainScreenNavigator }};
// Object.assign(mainScreens,otherScreens);

// const Navigator = StackNavigator(
//     mainScreens,{headerMode: 'none'}
// );


class DetailsScreen extends React.Component {
    render() {
      return (
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
          <Text>Details!</Text>
        </View>
      );
    }
  }

  class HomeScreen extends React.Component {
    componentDidMount() {
        let day = new Date("02/11/2011").getDay();
        console.log(day);
        DeviceEventEmitter.addListener('navigateToNewsDetail', this.navigateToNewsDetail);
        DeviceEventEmitter.addListener('AddCustomer', this.AddCustomer);
        DeviceEventEmitter.addListener('userCenterToElectronicBusiness', this.UserCenterToElectronicBusiness);//用户中心--->电子名片
        DeviceEventEmitter.addListener('userCenterToAdministrative', this.UserCenterToAdministrative);//用户中心跳转到行政审批
        DeviceEventEmitter.addListener('userCenterToOnlineLearning', this.serCenterToOnlineLearning);//用户中心跳转到在线学习
        DeviceEventEmitter.addListener('userCenterToAlerts', this.userCenterToAlerts); //用户中心跳转到消息通知
        DeviceEventEmitter.addListener('administrativeToOA', this.administrativeToOA); //我的申请->OA申请

        DeviceEventEmitter.addListener('CustomerDetails', this.toCustomerDetails);

        DeviceEventEmitter.addListener('AdministrativeOAToOaApplyForm', this.AdministrativeOAToOaApplyForm);//oa申请跳转到请假申请
        DeviceEventEmitter.addListener('AdministrativeOAToAddressChangeApply', this.AdministrativeOAToAddressChangeApply);//oa申请跳转到地址变更申请
        DeviceEventEmitter.addListener('AdministrativeOAToMbilePhoneChangeApply', this.AdministrativeOAToMbilePhoneChangeApply);//oa申请跳转到手机变更申请
        DeviceEventEmitter.addListener('AdministrativeOAToBankCardChangeApply', this.AdministrativeOAToBankCardChangeApply);//oa申请跳转到银行卡变更申请
        DeviceEventEmitter.addListener('AdministrativeOAToPromotionApply', this.AdministrativeOAToPromotionApply);//oa申请跳转到晋级申请
        DeviceEventEmitter.addListener('AdministrativeOAToComplexEffectApply', this.AdministrativeOAToComplexEffectApply); //oa申请跳转到复效申请
        DeviceEventEmitter.addListener('AdministrativeOAToQuiteApply', this.AdministrativeOAToQuiteApply);//oa申请跳转到离职申请
        DeviceEventEmitter.addListener('AddAddressPage', this.AddAddressPage);//地址变更申请-》新增地址页面
        DeviceEventEmitter.addListener('AddAddressPhonePage', this.AddAddressPhonePage); //添加地址页面-》添加手机号码页面
        DeviceEventEmitter.addListener('AddBankCardPage', this.AddBankCardPage);//银行变更申请申请-》添加银行页面
        DeviceEventEmitter.addListener('AdministrativeToOaApplyFormDetail', this.AdministrativeToOaApplyFormDetail); //我的申请-》申请详情
        DeviceEventEmitter.addListener('BankCardBindingDetailsPage', this.BankCardBindingDetailsPage); //添加银行页面-》银行绑定页面
        DeviceEventEmitter.addListener('BankCardReleaseBindingDetailsPage', this.BankCardReleaseBindingDetailsPage); //银行变更申请申请-》银行解除绑定页面
        DeviceEventEmitter.addListener('NewMobilePhoneChangeApplyPage', this.NewMobilePhoneChangeApplyPage); //银行变更申请申请-》新手机码页面
        // BackHandler.addEventListener('hardwareBackPress', this.onBackButtonPressAndroid);
        DeviceEventEmitter.addListener('PromotionNoticePage', this.PromotionNoticePage);  //晋级详情页面-》晋级通知书页面
        DeviceEventEmitter.addListener('PromotionNoticeAutographPage', this.PromotionNoticeAutographPage); //晋级通知书页面-》晋级签字页面
        DeviceEventEmitter.addListener('JobProof', this.JobProofPage);//我的申请-》工作证明
        DeviceEventEmitter.addListener('IncomeProof', ()=>{this.props.navigation.navigate('IncomeProof')}); //我的申请-》收入证明
        DeviceEventEmitter.addListener('OtherApply', ()=>{this.props.navigation.navigate('OtherApply')}); //我的申请-》其他申请
        DeviceEventEmitter.addListener('OtherProve', ()=>{this.props.navigation.navigate('OtherProve')}); //我的申请-》其他证明

        }

    componentWillUnmount() {
        DeviceEventEmitter.removeListener('navigateToNewsDetail', this.navigateToNewsDetail);
        DeviceEventEmitter.removeListener('AddCustomer', this.AddCustomer);
        DeviceEventEmitter.removeListener('userCenterToElectronicBusiness', this.UserCenterToElectronicBusiness);//用户中心--->电子名片
        DeviceEventEmitter.removeListener('userCenterToAdministrative', this.UserCenterToAdministrative);//用户中心跳转到行政审批
        DeviceEventEmitter.removeListener('userCenterToOnlineLearning', this.serCenterToOnlineLearning);//用户中心跳转到在线学习
        DeviceEventEmitter.removeListener('userCenterToAlerts', this.userCenterToAlerts); //用户中心跳转到消息通知
        DeviceEventEmitter.removeListener('administrativeToOA', this.administrativeToOA); //我的申请->OA申请


        DeviceEventEmitter.removeListener('AdministrativeOAToOaApplyForm', this.AdministrativeOAToOaApplyForm);//oa申请跳转到请假申请
        DeviceEventEmitter.removeListener('AdministrativeOAToAddressChangeApply', this.AdministrativeOAToAddressChangeApply);//oa申请跳转到地址变更申请
        DeviceEventEmitter.removeListener('AdministrativeOAToMbilePhoneChangeApply', this.AdministrativeOAToMbilePhoneChangeApply);//oa申请跳转到手机变更申请
        DeviceEventEmitter.removeListener('AdministrativeOAToBankCardChangeApply', this.AdministrativeOAToBankCardChangeApply);//oa申请跳转到银行卡变更申请
        DeviceEventEmitter.removeListener('AdministrativeOAToPromotionApply', this.AdministrativeOAToPromotionApply);//oa申请跳转到晋级申请
        DeviceEventEmitter.removeListener('AdministrativeOAToComplexEffectApply', this.AdministrativeOAToComplexEffectApply); //oa申请跳转到复效申请
        DeviceEventEmitter.removeListener('AdministrativeOAToQuiteApply', this.AdministrativeOAToQuiteApply);//oa申请跳转到离职申请
        DeviceEventEmitter.removeListener('AddAddressPage', this.AddAddressPage);//地址变更申请调到新增地址页面
        DeviceEventEmitter.removeListener('AddAddressPhonePage', this.AddAddressPhonePage); //添加地址页面-》添加手机号码页面
        DeviceEventEmitter.removeListener('AddBankCardPage', this.AddBankCardPage);//银行变更申请申请调添加银行页面
        DeviceEventEmitter.removeListener('BankCardBindingDetailsPage', this.BankCardBindingDetailsPage); //添加银行页面-》银行绑定页面
        DeviceEventEmitter.removeListener('BankCardReleaseBindingDetailsPage', this.BankCardReleaseBindingDetailsPage); //银行变更申请申请-》银行解除绑定页面
        // BackHandler.removeEventListener('hardwareBackPress', this.onBackButtonPressAndroid);
        DeviceEventEmitter.removeListener('AdministrativeToOaApplyFormDetail', this.AdministrativeToOaApplyFormDetail); //我的申请-》申请详情
        DeviceEventEmitter.removeListener('JobProof', this.JobProofPage);//我的申请-》工作证明
        DeviceEventEmitter.removeListener('NewMobilePhoneChangeApplyPage', this.NewMobilePhoneChangeApplyPage); //银行变更申请申请-》新手机码页面
        DeviceEventEmitter.removeListener('PromotionNoticePage', this.PromotionNoticePage);  //晋级详情页面-》晋级通知书页面
        DeviceEventEmitter.removeListener('PromotionNoticeAutographPage', this.PromotionNoticeAutographPage); //晋级通知书页面-》晋级签字页面
    }
    navigateToNewsDetail = ()=>{
        console.log('received message to navigateToNewsDetail');
        this.props.navigation.navigate('SettingsScreen');
    }
    AddCustomer = ()=>{
          console.log('received message to AddCustomer');
          this.props.navigation.navigate('AddCustomerScreen');
    }
      //用户中心--->电子名片
      UserCenterToElectronicBusiness = ()=>{
          console.log('received message to UserCenterToElectronicBusiness');
          this.props.navigation.navigate('ElectronicBusinessCard');
      }
        //用户中心跳转到行政审批
      UserCenterToAdministrative = ()=>{
          console.log('received message to UserCenterToAdministrative');
          this.props.navigation.navigate('Administrative');
      }
      //用户中心跳转到在线学习
      serCenterToOnlineLearning = ()=>{
          console.log('received message to serCenterToOnlineLearning');
          this.props.navigation.navigate('MyLearning');
      }
      //用户中心跳转到消息通知
      userCenterToAlerts = ()=>{
          console.log('received message to userCenterToAlerts');
          this.props.navigation.navigate('MyNew');
      }
      //我的申请->OA申请
      toCustomerDetails = ()=>{
          console.log('received message to toCustomerDetails');
          this.props.navigation.navigate('CustomerDetails');
      }

      administrativeToOA = ()=>{
          console.log('received message to administrativeToOA');
          this.props.navigation.navigate('AdministrativeOA');
      }
      //oa申请跳转到请假申请
      AdministrativeOAToOaApplyForm = ()=>{
          console.log('received message to AdministrativeOAToOaApplyForm');
          this.props.navigation.navigate('OaApplyForm');
      }
      //oa申请跳转到地址变更申请
      AdministrativeOAToAddressChangeApply = ()=>{
          console.log('received message to AdministrativeOAToAddressChangeApply');
          this.props.navigation.navigate('AddressChangeApply');
      }
      //oa申请跳转到手机变更申请
      AdministrativeOAToMbilePhoneChangeApply = ()=>{
          console.log('received message to AdministrativeOAToMbilePhoneChangeApply');
          this.props.navigation.navigate('MobilePhoneChangeApply');
      }
      //oa申请跳转到银行卡变更申请
      AdministrativeOAToBankCardChangeApply = ()=>{
          console.log('received message to AdministrativeOAToBankCardChangeApply');
          this.props.navigation.navigate('BankCardChangeApply');
      }
      //oa申请跳转到晋级申请
      AdministrativeOAToPromotionApply = ()=>{
          console.log('received message to AdministrativeOAToPromotionApply');
          this.props.navigation.navigate('PromotionApply');
      }
      //oa申请跳转到复效申请
      AdministrativeOAToComplexEffectApply = ()=>{
          console.log('received message to AdministrativeOAToComplexEffectApply');
          this.props.navigation.navigate('ComplexEffectApply');
      }
      //oa申请跳转到离职申请
      AdministrativeOAToQuiteApply = ()=>{
          console.log('received message to AdministrativeOAToQuiteApply');
          this.props.navigation.navigate('QuitApply');
      }
      //地址变更申请调到新增地址页面
      AddAddressPage = ()=>{
          console.log('received message to AddAddressPage');
          this.props.navigation.navigate('AddAddress');
      }
      //添加地址页面-》添加手机号码页面
      AddAddressPhonePage = ()=>{
          console.log('received message to AddAddressPhonePage');
          this.props.navigation.navigate('AddAddressPhonePage');
      }
      //银行变更申请申请调添加银行页面
      AddBankCardPage = ()=>{
          console.log('received message to AddBankCardPage');
          this.props.navigation.navigate('AddBankCardPage');
      }
      //添加银行页面-》银行绑定页面
      BankCardBindingDetailsPage = ()=>{
          console.log('received message to BankCardBindingDetailsPage');
          this.props.navigation.navigate('BankCardBindingDetailsPage');
      }
      //银行变更申请申请-》银行解除绑定页面
      BankCardReleaseBindingDetailsPage = ()=>{
          console.log('received message to BankCardReleaseBindingDetailsPage');
          this.props.navigation.navigate('BankCardReleaseBindingDetailsPage');
      }

      //我的申请-》申请详情
      AdministrativeToOaApplyFormDetail= (value)=>{
          console.log('received message to AdministrativeToOaApplyFormDetail');
          this.props.navigation.navigate('AdministrativeToOaApplyFormDetail',value);
      }
      //我的申请-》工作证明
      JobProofPage= ()=>{
        console.log('received message to JobProofPage');
        this.props.navigation.navigate('JobProofPage');
      }

      //银行变更申请申请-》新手机码页面
      NewMobilePhoneChangeApplyPage= ()=>{
          console.log('received message to NewMobilePhoneChangeApplyPage');
          this.props.navigation.navigate('NewMobilePhoneChangeApplyPage');
      }

      //我的申请-》其它申请
      JobProofPage= ()=>{
        // console.log('received message to JobProofPage');
        this.props.navigation.navigate('JobProofPage');
      }
      
      //晋级详情页面-》晋级通知书页面
      PromotionNoticePage= ()=>{
          console.log('received message to PromotionNoticePage');
          this.props.navigation.navigate('PromotionNoticePage');
      }
      //晋级通知书页面-》晋级签字页面
      PromotionNoticeAutographPage= ()=>{
          console.log('received message to PromotionNoticeAutographPage');
          this.props.navigation.navigate('PromotionNoticeAutographPage');
      }

      onBackButtonPressAndroid = () => {
        // return true;
        // this.props.navigation.
        // this.props.navigation.goBack(null);
     };

    render() {
      return (
        <MainScreen style={{flex:1}}></MainScreen>
        //   <View style={{ flex: 1 }}>
        //       <Button title="Go to Details" onPress={this.navigateToNewsDetail} />
        //   </View>
      );
    }
  }

  class SettingsScreen extends React.Component {
    render() {
      return (
        <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
          <Button
            title="Go to Details"
            onPress={() => this.props.navigation.goBack()}
          />
        </View>
      );
    }
  }

const AppScreenStack  = StackNavigator({
    home: {
      screen: HomeScreen,
    },
    SettingsScreen: {
      screen: SettingsScreen,
    },
    AddCustomerScreen: {
        screen: AddVisitCustomer,
    },
    ElectronicBusinessCard: {//电子名片
        screen: ElectronicBusinessCard,
    },
    MyLearning: {//在线学习
        screen: MyLearning,
    },
    MyNew: {//消息通知
        screen: MyNew,
    },
    Administrative: {//行政审批
        screen: Administrative,
    },
    AdministrativeOA: {//OA申请
        screen: AdministrativeOA,
    },
    OaApplyForm: {//请假申请
        screen: OaApplyForm,
    },
    AddressChangeApply: {//地址变更申请
        screen: AddressChangeApply,
    },
    MobilePhoneChangeApply: {//手机变更申请
        screen: MobilePhoneChangeApply,
    },
    BankCardChangeApply: {//银行卡变更申请
        screen: BankCardChangeApply,
    },
    PromotionApply: {//晋级申请
        screen: PromotionApply,
    },
    ComplexEffectApply: {//复效申请
        screen: ComplexEffectApply,
    },
    QuitApply: {//离职申请
        screen: QuitApply,
    },
    AddAddress: {//新增地址页面
        screen: AddAddress,
    },
    AddAddressPhonePage: {//添加手机号码页面
        screen: AddAddressPhone,
    },
    AddBankCardPage: {//添加银行页面
        screen: AddBankCard,
    },BankCardBindingDetailsPage: {//绑定银行页面
        screen: BankCardBindingDetails,
    },BankCardReleaseBindingDetailsPage: {//绑定银行页面
        screen: BankCardReleaseBindingDetails,
    },CustomerDetails:{//客户详情
        screen:CustomerDetails,
    },JobProofPage:{//工作证明
        screen:JobProof,
    },
    IncomeProof:{//收入证明
        screen:IncomeProof,
    },
    AdministrativeToOaApplyFormDetail:{//申请详情
        screen:OaApplyFormDetail,
    },

    OtherApply:{//其他证明
        screen:OtherApply,
    },
    OtherProve:{//万能申请
        screen:OtherProve,
    },

    NewMobilePhoneChangeApplyPage:{ //新手机号变更
        screen:NewMobilePhoneChangeApply,
    },

    PromotionNoticePage :{    //晋级通知书
        screen:PromotionNotice,
    },

    PromotionNoticeAutographPage: { //晋级签字
        screen:PromotionNoticeAutograph,
    }
  },
{
    headerMode: 'none',
  });
  export default class Appcompnent extends React.Component {
    componentDidMount() {
      DeviceEventEmitter.addListener('onNotification', this.onNotificationHandler);
      // BackHandler.addEventListener('hardwareBackPress', this.onBackButtonPressAndroid);
    }

  componentWillUnmount() {
      DeviceEventEmitter.removeListener('onNotification', this.onNotificationHandler);
      // BackHandler.removeEventListener('hardwareBackPress', this.onBackButtonPressAndroid);
  }

  onNotificationHandler(e){
    alert("Notification Received.Title:" + e.title + ", Content:" + e.content);
  }

    render() {
        return (<AppScreenStack/>)
    }
  }
// export default class RootScene extends React.Component {

//     componentDidMount() {
//         DeviceEventEmitter.addListener('onNotification', this.onNotification);
//     }
 
//     componentWillUnmount() {
//         DeviceEventEmitter.removeListener('onNotification', this.onNotification);
//     }

//     onNotification(e){
//         alert("Notification Received.Title:" + e.title + ", Content:" + e.content);
//         console.log("Notification Received.Title:" + e.title + ", Content:" + e.content);
//     }

//     render() {
//         return (
//                 <View style={{flex: 1}}>
//                     <Navigator />
//                 </View>

//         )
//     }
// }

