import React, { Component } from 'react';
import { AppRegistry, View,Text,StyleSheet} from 'react-native';
import {AchvMainContent} from '../components/achievement/AchvMainContent'

export  class Achievement extends Component {
  render() {
    return (
      // <View style={styles.container}>
      //   <View style={styles.header}>
      //     <View style={ {flex:1,width:60}}/>
      //     <View style={ {flex:1,width:60,justifyContent: 'center'}}> 
      //       <Text style={ styles.headerLabel}>业绩</Text> 
      //     </View>
      //     <View style={ {flex:1,width:60,justifyContent: 'center',}}> 
      //       <Text style={ styles.headerEmail}>Email</Text> 
      //     </View>
      //   </View>
      //   <AchvMainContent />
      // </View>
      <AchvMainContent />
    );
  }
};


const styles = StyleSheet.create({
  container :{
    flexDirection: 'column',
  },
  header: {
    height: 50, 
    backgroundColor: '#BCBCBC',
    flexDirection: 'row'
  },
  headerLabel: {
    fontSize: 18,
    color:'white' ,
    textAlign:'center'
  },
  headerEmail: {
    textAlign:'right',
    paddingRight:10
  }
});