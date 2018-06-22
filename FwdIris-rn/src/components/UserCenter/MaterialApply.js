import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback} from 'react-native';
import CheckBox from 'react-native-checkbox';


var Dimensions = require('Dimensions');
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

export class MaterialApply extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            isSe1:false,
            isSe2:false,
            isSe3:false,
            isSe4:false
        }
    }
    render() {
        return (
            <ScrollView style={{backgroundColor:'white',}}>
                <View style={{backgroundColor:'#F2F2F2'}}>
                    <View style={{backgroundColor:'#D1D1D1',}}>
                        <View style={{justifyContent:'center',alignItems: 'center',flex:1,}}>
                            <Image style={{width: 100, height: 100,borderRadius:50,marginTop:20,}} source={require('../../../img/UserCenter/UserImage.jpeg')} />
                        </View>
                        <View style={{flexDirection: 'row',marginTop:10,}}>
                            <View style={{flex:1,}}>
                                <TouchableWithoutFeedback  onPress={() => this.props.navigation.goBack()}>
                                    <Image style={{width:30,height:30,marginTop:5,marginLeft:15,marginBottom:10,}}source={require('../../../img/UserCenter/GoBack.png')}/>
                                </TouchableWithoutFeedback>
                            </View>
                            <Text style={{flex:1,marginLeft:-50,color:'white',fontWeight:'bold',fontSize: 15,marginTop:5,}}>七月流火</Text>
                        </View>
                    </View>
                    <View  style={{flex:1,backgroundColor:'#FFFFFF'}}>
                        <Text style={{fontSize:18,color:'#FF9900',textAlign:'center',height:35,lineHeight:35}}>材料申请</Text>
                    </View>
                    <View  style={{flexDirection: 'row',justifyContent: 'space-between'}}>
                        <Text  style={{flex:2,marginLeft:20,lineHeight:50}}>证明类型</Text>
                        <Text style={{flex:7,height: 40}}></Text>
                        <Text  style={{flex:1}}></Text>
                    </View>
                    <View style={{flexDirection: 'row',justifyContent: 'space-between'}}>
                        <View  style={{textAlign:'center',flex:5,marginLeft:20}}>
                            <CheckBox
                                label='工作经历证明'
                                checked={this.state.isSe1}
                                onChange={(checked) => {
                                    this.setState({
                                        isSe1:!checked
                                    });
                                }}
                            />
                        </View>
                        <View  style={{textAlign:'center',flex:5}}>
                            <CheckBox
                                label='荣誉证明'
                                checked={this.state.isSe2}
                                onChange={(checked) => {
                                    this.setState({
                                        isSe2:!checked
                                    });
                                }}
                            />
                        </View>
                    </View>
                    <View style={{flexDirection: 'row',justifyContent: 'space-between'}}>
                        <View  style={{textAlign:'center',flex:5,marginLeft:20}}>
                            <CheckBox
                                label='工作经历证明'
                                checked={this.state.isSe3}
                                onChange={(checked) => {
                                    this.setState({
                                        isSe3:!checked
                                    });
                                }}
                            />
                        </View>
                        <View  style={{textAlign:'center',flex:5}}>
                            <CheckBox
                                label='荣誉证明'
                                checked={this.state.isSe4}
                                onChange={(checked) => {
                                    this.setState({
                                        isSe4:!checked
                                    });
                                }}
                            />
                        </View>
                    </View>
                    <View  style={{flexDirection: 'row',justifyContent: 'space-between',paddingTop:10}}>
                        <Text  style={{flex:2,marginLeft:20,lineHeight:50}}>说明</Text>
                        <Text style={{flex:7,height: 40}}></Text>
                        <Text  style={{flex:1}}></Text>
                    </View>
                    <View style={{flexDirection: 'row',justifyContent: 'space-between',paddingTop:10}}>
                        <TextInput
                            underlineColorAndroid='transparent'
                            multiline={true}
                            style={{width:380,marginLeft:20,height:150,borderColor: 'gray', borderWidth: 1,flex:9,backgroundColor:"#FFFFFF"}}></TextInput>
                        <Text  style={{flex:1}}></Text>
                    </View>
                    <View  style={{flexDirection: 'row',justifyContent: 'space-between',marginTop:20}}>
                        <Text style={{flex:1}}></Text>
                        <Text style={{flex:4,width: 100,height: 40,lineHeight:40,textAlign:'center',borderColor: 'gray', borderWidth: 1,backgroundColor:'#FFFFFF',borderRadius:10}}>提交</Text>
                        <Text style={{flex:4, width: 100,height: 40,lineHeight:40,textAlign:'center',borderColor: 'gray', borderWidth: 1,backgroundColor:'#FFFFFF',borderRadius:10,marginLeft:10}}>取消</Text>
                        <Text  style={{flex:1}}></Text>
                    </View>
                    <View  style={{flexDirection: 'row',justifyContent: 'space-between',marginTop:20}}></View>
                </View>
            </ScrollView>
        );
    }
}
const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#f3f2f2'
    },
    item: {
        flexDirection: 'row',
    },
    line: {
        flex: 1,
        height: 0.3,
        backgroundColor: 'darkgray',
    },
})

