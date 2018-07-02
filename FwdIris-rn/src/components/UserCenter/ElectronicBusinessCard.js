import React from 'react';
import {StyleSheet, ScrollView, Text ,View,Image,Button,ImageBackground,TouchableWithoutFeedback,Platform} from 'react-native';
export class ElectronicBusinessCard extends React.Component {
    render() {
        return (
            <ScrollView style={{backgroundColor:'#999999',}}>
                {Platform.OS === "ios" ?<View style={{backgroundColor:'#323232',height:20,}}></View>:<View></View>}
                <ImageBackground style={{ flexDirection: 'row',justifyContent: 'space-between',height:44}} source={require('../../../img/UserCenter/BackgroundTitleImage.png')}>
                    <TouchableWithoutFeedback  onPress={() => this.props.navigation.goBack()}>
                        <Image style={{width:30,height:30,marginTop:5,marginLeft:5,}}source={require('../../../img/UserCenter/GoBack.png')}/>
                    </TouchableWithoutFeedback>
                    <View style={{flexDirection: 'row',marginTop:10,}}>
                        <Image style={{width:25,height:25,marginRight:15,}}source={require('../../../img/UserCenter/Edited.png')}/>
                        <Image style={{width:25,height:25,marginRight:15,}} source={require('../../../img/UserCenter/Shard.png')}/>
                    </View>
                </ImageBackground>

                <ImageBackground style={{height:200}} source={require('../../../img/UserCenter/BackgroundCenter.png')}>
                </ImageBackground>
                <View style={{justifyContent:'center',alignItems: 'center',flex:1,}}>
                    <Image style={{width: 100, height: 100,borderRadius:50,marginTop:-50,}} source={require('../../../img/UserCenter/UserImage.jpeg')} />
                </View>
               <View style={{alignItems: 'center',color:'white',}}>
                   <Text style={{color:'white',fontWeight:'bold',fontSize: 30,}}>Jason Wang</Text>
                   <Text style={{color:'white',fontWeight:'bold',fontSize: 15,marginTop:10,}}>高级寿险销售总监</Text>
                   <Text style={{color:'white',fontWeight:'bold',fontSize: 15,marginTop:10,}}>上海分公司 徐汇营管处</Text>
               </View>
                <View style={{flexDirection: 'row',marginLeft:50,marginTop:30,}}>
                    <Image style={{width:25,height:25,marginRight:15,}}source={require('../../../img/UserCenter/TelPhone.png')}/>
                    <Text style={{color:'white',fontWeight:'bold',fontSize: 15,}}>13312345678</Text>
                </View>
                <View style={{flexDirection: 'row',marginLeft:50,marginTop:30,}}>
                    <Image style={{width:25,height:25,marginRight:15,}}source={require('../../../img/UserCenter/EmailWhite.png')}/>
                    <Text style={{color:'white',fontWeight:'bold',fontSize: 15,}}>jaswon.wang@*****.com.cn</Text>
                </View>



                <View style={{alignItems: 'center',color:'white',marginLeft:15,marginRight:15, borderBottomColor:'white',borderBottomWidth:1,borderBottomStyle:'sold',}}>
                    <Text style={{color:'white',fontWeight:'bold',fontSize:15,marginTop:15,paddingBottom:10,}}>个人荣誉</Text>
                </View>
                <View style={{flexDirection: 'row',marginBottom:10,}}>
                    <View style={{flex:1,marginLeft:30,marginTop:20,}}>
                        <Image style={{width:80,height:80,}} source={require('../../../img/UserCenter/rongyu.png')}/>
                        <Text style={styles.honorText}>介绍1</Text>
                        <Text style={styles.honorText}>介绍2</Text>
                        <Text style={styles.honorText}>介绍3</Text>
                        <Text style={styles.honorText}>介绍4</Text>
                    </View>
                    <View style={{flex:1,marginLeft:30,marginTop:20,}}>
                        <Image style={{width:80,height:80,}} source={require('../../../img/UserCenter/rongyu.png')}/>
                        <Text style={styles.honorText}>介绍1</Text>
                        <Text style={styles.honorText}>介绍2</Text>
                        <Text style={styles.honorText}>介绍3</Text>
                        <Text style={styles.honorText}>介绍4</Text>
                    </View>
                </View>
                <View style={{flexDirection: 'row',marginBottom:10,}}>
                    <View style={{flex:1,marginLeft:30,marginTop:20,}}>
                        <Image style={{width:80,height:80,}} source={require('../../../img/UserCenter/rongyu.png')}/>
                        <Text style={styles.honorText}>介绍1</Text>
                        <Text style={styles.honorText}>介绍2</Text>
                        <Text style={styles.honorText}>介绍3</Text>
                        <Text style={styles.honorText}>介绍4</Text>
                    </View>
                </View>
            </ScrollView>


        );
    }
}
const styles = StyleSheet.create({
    container: {flex: 1,},
    honorText:{color:'white',fontWeight:'bold',fontSize: 15,marginTop:5,},
});