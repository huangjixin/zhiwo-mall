import {StyleSheet,
    Dimensions
  } from 'react-native';
import ScreenUtil from '../../../utils/ScreenUtil';

// 头部高度。
const headerHeight = Dimensions.get('screen').height/5;
const UserScreenStyle = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#ffffff',
      // alignItems: 'center',
      // justifyContent: 'center',
    },
    header:{
      flex: 1,
      height:headerHeight,
      backgroundColor:'red',
      flexDirection:'row',
      alignItems: 'center',
      justifyContent: 'center',
    },
    headerContent:{
      flex: 1,
      paddingLeft: 10,
      paddingRight: 10,
      flexDirection:'row',
      alignItems: 'center',
      justifyContent: 'flex-start',
    },
    headerContentIcon:{
      width: 80,height:80,borderRadius: 40,marginRight: 8
    },
    userLabel:{
      color:'#ffffff',
      fontSize:16,
      marginRight:10
    },
    menu:{
      backgroundColor:'#ffffff',
      paddingLeft:10,
      paddingRight:10,
    },
    menuItem:{
      flexDirection:'row',
      alignItems: 'center',
      justifyContent: 'flex-start',
      paddingTop: 5,
      paddingBottom: 5,
      borderBottomColor: '#E6E6E6',
      borderBottomWidth: 1
    },menuItemLeft:{
      flex:1,
      flexDirection:'row',
      alignItems: 'center',
      justifyContent: 'flex-start',
    },menuItemRight:{
      flex:1,
      flexDirection:'row',
      alignItems: 'center',
      justifyContent: 'flex-end',
    },
    menuItemText:{
     fontSize:ScreenUtil.setSpText2(18)
    },
    // 登录按钮样式。
    loginOutBtnBg: {
        backgroundColor:'red',
        borderRadius:ScreenUtil.scaleSize(4),
        flex:1,
        paddingBottom:ScreenUtil.scaleSize(8),
        paddingTop:ScreenUtil.scaleSize(8),
        flexDirection:"row",
        alignItems:"center",
        justifyContent:"center"
    },
    loginOutBtn: {
        color:"#ffffff",
        fontSize:ScreenUtil.setSpText2(16)
    }
});

export default {
    UserScreenStyle
}