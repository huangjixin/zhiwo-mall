import {StyleSheet,
    Dimensions
  } from 'react-native';
import ScreenUtil from '../../../utils/ScreenUtil';

// 头部高度。
const headerHeight = Dimensions.get('screen').height/5;
const SignInScreenStyle = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#ffffff',
      alignItems: 'center',
      justifyContent: 'center',
    },
    // 登录按钮样式。
    loginBtnBg: {
       // flexDirection:'row',
        backgroundColor:'#757575',
        borderRadius:ScreenUtil.scaleSize(4),
        // flex:0.5,
        paddingBottom:ScreenUtil.scaleSize(8),
        paddingTop:ScreenUtil.scaleSize(8),
        paddingLeft:ScreenUtil.scaleSize(20),
        paddingRight: ScreenUtil.scaleSize(20),
        alignItems:"center",
        justifyContent:"center"
    },
    loginBtn: {
        color:"#ffffff",
        fontSize:ScreenUtil.setSpText2(16)
    }
});

export default {
    SignInScreenStyle
}