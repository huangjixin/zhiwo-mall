import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image} from 'react-native';
import {UserCenterMain} from "../components/UserCenter/UserCenterMain";

var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class UserCenter extends React.Component {

  render() {
    return (
        <UserCenterMain/>
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
    bottomTotalView:{flexDirection: 'column',justifyContent: 'center',alignItems: 'center'},
});


