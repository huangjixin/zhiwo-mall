import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image} from 'react-native';
class BottomBalendarList extends React.Component {

    render() {
        return (
            <View >
                <View style={{flexDirection: 'row',marginTop:10,backgroundColor:'#F2F2F2',}}>
                    <Image  source={require('../../../img/Calendar/biaoqian.png')}
                            style={{width:20,height:20,marginLeft:10,marginRight:10,}}/>
                    <Text style={{fontSize:15,fontWeight:'bold'}}>今天</Text>
                </View>
                <View style={styles.BorderColor}>
                    <View style={{margin:10,}}>
                        <Text style={{color:'#A3A3A3',}}>09:00</Text>
                        <Text style={{fontSize:15,}}>IRIS项目周例会</Text>
                    </View>
                    <View style={{alignItems: 'center',margin:10,}}>
                        <Image  source={require('../../../img/Calendar/u1142.png')}
                                style={{width:20,height:20,}}/>
                        <Text style={{fontSize:8,}}>Hours-Minutes-Seconds</Text>
                        <View style={{marginRight:10,flexDirection: 'row',justifyContent: 'space-between',}}>
                            <Text style={styles.timeTextColor}>02</Text>
                            <Text style={styles.timeTextColor}>02</Text>
                            <Text style={styles.timeTextColor}>02</Text>
                        </View>
                    </View>
                </View>

                <View style={styles.BorderColor}>
                    <View style={{margin:10,}}>
                        <Text style={{}}>09:00</Text>
                        <Text style={{fontSize:15,}}>IRIS项目周例会</Text>
                    </View>
                    <View style={{alignItems: 'center',margin:10,}}>
                        <Image  source={require('../../../img/Calendar/u1142.png')}
                                style={{width:20,height:20,}}/>
                        <Text style={{fontSize:8,}}>Hours-Minutes-Seconds</Text>
                        <View style={{marginRight:10,flexDirection: 'row',justifyContent: 'space-between',}}>
                            <Text style={styles.timeTextColor}>02</Text>
                            <Text style={styles.timeTextColor}>02</Text>
                            <Text style={styles.timeTextColor}>02</Text>
                        </View>
                    </View>
                </View>

                <View style={{flexDirection: 'row',marginTop:10,backgroundColor:'#F2F2F2',}}>
                    <Image  source={require('../../../img/Calendar/biaoqian.png')}
                            style={{width:20,height:20,marginLeft:10,marginRight:10,}}/>
                    <Text style={{fontSize:15,fontWeight:'bold'}}>今天</Text>
                </View>
                <View style={styles.BorderColor}>
                    <View style={{margin:10,}}>
                        <Text style={{color:'#A3A3A3',}}>09:00</Text>
                        <Text style={{fontSize:15,}}>IRIS项目周例会</Text>
                    </View>
                    <View style={{alignItems: 'center',margin:10,}}>
                        <Image  source={require('../../../img/Calendar/u1142.png')}
                                style={{width:20,height:20,}}/>
                        <Text style={{fontSize:8,}}>Hours-Minutes-Seconds</Text>
                        <View style={{marginRight:10,flexDirection: 'row',justifyContent: 'space-between',}}>
                            <Text style={styles.timeTextColor}>02</Text>
                            <Text style={styles.timeTextColor}>02</Text>
                            <Text style={styles.timeTextColor}>02</Text>
                        </View>
                    </View>
                </View>

                <View style={styles.BorderColor}>
                    <View style={{margin:10,}}>
                        <Text style={{}}>09:00</Text>
                        <Text style={{fontSize:15,}}>IRIS项目周例会</Text>
                    </View>
                    <View style={{alignItems: 'center',margin:10,}}>
                        <Image  source={require('../../../img/Calendar/u1142.png')}
                                style={{width:20,height:20,}}/>
                        <Text style={{fontSize:8,}}>Hours-Minutes-Seconds</Text>
                        <View style={{marginRight:10,flexDirection: 'row',justifyContent: 'space-between',}}>
                            <Text style={styles.timeTextColor}>02</Text>
                            <Text style={styles.timeTextColor}>02</Text>
                            <Text style={styles.timeTextColor}>02</Text>
                        </View>
                    </View>
                </View>
            </View>
        );
    }

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    timeTextColor:{backgroundColor:'#FF9300',color:'white',width:25,textAlign:'center',marginLeft:5,},
    BorderColor:{flexDirection: 'row',justifyContent: 'space-between',borderStyle:'solid',
        borderWidth:0.5,marginLeft:10,marginRight:10,
    borderLeftWidth:3,
        borderLeftColor:'#FF9300',
    borderRightColor:'gray',borderTopColor:'gray',borderBottomColor:'gray',
    backgroundColor:'white',marginTop:10,
    }
});

export default BottomBalendarList;
