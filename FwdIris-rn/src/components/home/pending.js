import React from 'react';
import {StyleSheet, ScrollView, Text,View} from 'react-native';


export  class Pending extends React.Component {

    render() {
        return (
            <View>
                <View style={{backgroundColor:'#F2F2F2',height:5}}></View>
                <View style={{flex:1, flexDirection: 'row',justifyContent: 'flex-start',marginTop:10}}>
                    <View style={{flex: 1}}>
                        <Text style={{textAlign:'center',paddingTop:20 ,borderRadius:80,backgroundColor:'#FF9900',width:80,height:80,color:'#ffffff',fontSize:30}}>新</Text>
                    </View>
                    <View style={{flex: 3,marginLeft:10}}>
                        <Text style={{fontSize:16}}>P123456789012</Text>
                        <Text>投保人：Jason Tang   被保人：Clould Wang</Text>
                        <Text>由于投被保人投保日期变更，需要提交身份证复印件。</Text>
                    </View>
                </View>
                <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>

                <View style={{flex:1, flexDirection: 'row',justifyContent: 'flex-start',marginTop:10}}>
                    <View style={{flex: 1}}>
                        <Text style={{textAlign:'center',paddingTop:20 ,borderRadius:80,backgroundColor:'#99C2EB',width:80,height:80,color:'#ffffff',fontSize:30}}>保</Text>
                    </View>
                    <View style={{flex: 3,marginLeft:10}}>
                        <Text style={{fontSize:16}}>P123456789012</Text>
                        <Text>投保人：Jason Tang   被保人：Clould Wang</Text>
                        <Text>由于投被保人投保日期变更，需要提交身份证复印件。</Text>
                    </View>
                </View>
                <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>

                <View style={{flex:1, flexDirection: 'row',justifyContent: 'flex-start',marginTop:10}}>
                    <View style={{flex: 1}}>
                        <Text style={{textAlign:'center',paddingTop:20 ,borderRadius:80,backgroundColor:'#D6C299',width:80,height:80,color:'#ffffff',fontSize:30}}>理</Text>
                    </View>
                    <View style={{flex: 3,marginLeft:10}}>
                        <Text style={{fontSize:16}}>P123456789012</Text>
                        <Text>投保人：Jason Tang   被保人：Clould Wang</Text>
                        <Text>由于投被保人投保日期变更，需要提交身份证复印件。</Text>
                    </View>
                </View>
                <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>

                <View style={{flex:1, flexDirection: 'row',justifyContent: 'flex-start',marginTop:10}}>
                    <View style={{flex: 1}}>
                        <Text style={{textAlign:'center',paddingTop:20 ,borderRadius:80,backgroundColor:'#EBAD99',width:80,height:80,color:'#ffffff',fontSize:30}}>其</Text>
                    </View>
                    <View style={{flex: 3,marginLeft:10}}>
                        <Text style={{fontSize:16}}>P123456789012</Text>
                        <Text>投保人：Jason Tang   被保人：Clould Wang</Text>
                        <Text>由于投被保人投保日期变更，需要提交身份证复印件。</Text>
                    </View>
                </View>
                <Text style={{borderBottomWidth:1,borderBottomColor:'#CCCCCC'}}/>

                <View style={{height:10}}></View>
            </View>
        );
    }
}