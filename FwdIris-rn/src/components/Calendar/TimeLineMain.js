import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image} from 'react-native';
import {Calendar} from 'react-native-calendars';
class TimeLineMain extends React.Component {

    render() {
        return (
            <ScrollView style={styles.container}>

                <View style={{flex: 1, flexDirection: 'row',
                    justifyContent: 'space-between',height:60,alignItems: 'center', borderBottom:'solid',borderColor:'rgb(242,242,242)',borderBottomWidth: 0.5,}}>
                    <View style={{flex: 1, flexDirection: 'row'}}>
                        <Image source={require('../../../img/Calendar/u572.png')}
                               style={{width:32,height:32,marginTop:10,marginLeft:10,}}/>
                        <Text style={{fontSize:15,marginTop:10}}>时间轴</Text>
                    </View>
                    <View style={{flex: 1, flexDirection: 'row',justifyContent: 'space-between',}}>
                        {/*<Image source={require('../../../img/Calendar/u567.png')}*/}
                               {/*style={{marginTop:10,marginRight:10,fontSize:10}}/>*/}
                        {/*<Text style={{fontSize:20,marginTop:10}}>一月2018</Text>*/}
                        {/*<Image source={require('../../../img/Calendar/u567.png')}*/}
                               {/*style={{marginTop:10,marginRight:10,fontSize:10}}/>*/}
                        <Text style={{fontSize:30,color:'rgb(255,147,0))',}}> &lt;</Text>
                        {<Text style={{fontSize:20,marginTop:10,color:'rgb(255,147,0))'}}>一月2018</Text>}
                        <Text style={{fontSize:30,color:'rgb(255,147,0))',}}>></Text>
                    </View>
                    <View style={{flex: 1,alignItems: 'flex-end',}}>
                        <Image source={require('../../../img/Calendar/u876.png')}
                               style={{marginTop:10,marginRight:10,}} />
                    </View>

                </View>
                <View>
                    <View style={{flexDirection: 'row',marginTop:10,}}>
                        <Text style={{width:80,borderRight:'solid',borderRightColor:'rgb(242,242,242)',borderRightWidth:0.5,textAlign:'center',paddingBottom:10,paddingTop:10,}}>时间</Text>
                        <Text style={{flex:4,marginRight:10,marginLeft:10,paddingBottom:10,paddingTop:10,}}>事件</Text>
                    </View>


                    <View style={{flexDirection: 'row',marginTop:10,}}>
                        <View style={{width:80,borderRight:'solid',borderRightColor:'rgb(242,242,242)',borderRightWidth:0.5,alignItems: 'center',paddingBottom:10,paddingTop:10,}}>
                            <Text>1/22</Text>
                            <Text>08:00 am</Text>
                        </View>
                        <Image source={require('../../../img/Calendar/u1461.png')}
                               style={{position:'absolute',marginTop:20,marginLeft:75,}}/>
                        <Text style={{flex:4,marginRight:10,marginLeft:10,backgroundColor:'rgb(255,147,0))',paddingBottom:10,paddingTop:10,paddingLeft:10,}}>Tom 的生日，记得到花店买束鲜花</Text>
                    </View>


                    <View style={{flexDirection: 'row',marginTop:10,}}>
                        <View style={{width:80,borderRight:'solid',borderRightColor:'rgb(242,242,242)',borderRightWidth:0.5,alignItems: 'center',paddingBottom:10,paddingTop:10,}}>
                            <Text>1/22</Text>
                            <Text>08:00 am</Text>
                        </View>
                        <Image source={require('../../../img/Calendar/u1473.png')}
                               style={{position:'absolute',marginTop:20,marginLeft:75,}}/>
                        <Text style={{flex:4,marginRight:10,marginLeft:10,backgroundColor:'rgb(242,242,242)',paddingBottom:10,paddingTop:10,paddingLeft:10,}}>Tom 的生日，记得到花店买束鲜花</Text>
                    </View>

                    <View style={{flexDirection: 'row',marginTop:10,}}>
                        <View style={{width:80,borderRight:'solid',borderRightColor:'rgb(242,242,242)',borderRightWidth:0.5,alignItems: 'center',paddingBottom:10,paddingTop:10,}}>
                            <Text>1/22</Text>
                            <Text>08:00 am</Text>
                        </View>
                        <Image source={require('../../../img/Calendar/u1473.png')}
                               style={{position:'absolute',marginTop:20,marginLeft:75,}}/>
                        <Text style={{flex:4,marginRight:10,marginLeft:10,backgroundColor:'rgb(242,242,242)',paddingBottom:10,paddingTop:10,paddingLeft:10,}}>Tom 的生日，记得到花店买束鲜花</Text>
                    </View>
                </View>



            </ScrollView>
        );
    }

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor:'white',
    },
});

export default TimeLineMain;
