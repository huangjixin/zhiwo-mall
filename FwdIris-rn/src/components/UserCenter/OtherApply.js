import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,Button,TouchableOpacity } from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";


export class OtherApply extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            purpose:''
        }
    }
    render() {
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <ApplyCommonHeader
                    title={'其他申请'}
                    onReturn={() => this.props.navigation.goBack()}
                />
                <View style={{flexDirection:'column',paddingLeft:10,backgroundColor:'#FFFFFF',paddingTop:10
                    ,marginBottom:20,paddingBottom:20,borderColor:'#F2F2F2',borderBottomWidth:1}}>
                    <View style={{marginBottom:10}}>
                        <Text style={{color:'#333333',fontSize:16}}>详细说明</Text>
                    </View>
                    <TouchableWithoutFeedback onPress={()=>{this.refs.refTextInput.focus(); }}>
                        <View style={{marginRight:10,height:100 ,borderColor:'#F1F1F1',borderWidth:1}}>
                            <TextInput
                                ref="refTextInput"
                                underlineColorAndroid='transparent'
                                editable = {true}
                                maxLength = {400}
                                multiline = {true}
                                onChangeText={(text) => {this.setState({purpose:text});}}
                            />
                        </View>
                    </TouchableWithoutFeedback>

                    <View style={{marginTop:10,marginBottom:10}}>
                        <Text style={{color:'#333333',fontSize:16}}>图片</Text>
                    </View>
                    <View style={{width:70,height:70,flexDirection:'row',marginRight:15,}}>
                        <Image style={{width:70,height:65,}} source={require('../../../img/UserCenter/addImage.png')} />
                    </View>
                </View>

                <TouchableWithoutFeedback onPress={()=>{this.setState({ isPreview: true});}}>
                    <View style={{justifyContent: 'center',alignItems: 'center',backgroundColor:'#FFFFFF',height:50
                        ,borderColor:'#F2F2F2',borderBottomWidth:1,borderTopWidth:1,}}>
                        <Text style={{color:'#E87722',fontSize:16}}>提交</Text>
                    </View>
                </TouchableWithoutFeedback>
            </ScrollView>
        );
    }

}

