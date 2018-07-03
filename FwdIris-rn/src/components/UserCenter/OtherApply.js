import React from 'react';
import {
    StyleSheet,
    ScrollView,
    Text,
    View,
    Image,
    TextInput,
    TouchableWithoutFeedback,
    Button,
    TouchableOpacity,
    Dimensions
} from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import Textarea from 'react-native-textarea';
import * as RequestURL from "../../common/RequestURL";
import Toast from './Toast/Toast';
import ImagePicker from "react-native-image-picker";
const imageWidth = (Dimensions.get('window').width - 15 * 5) / 3;
const screenWidth = Dimensions.get('window').width / 3 - 60;

export class OtherApply extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            id:'',
            description:'',
            imageArray: [], //图片数组
            type:'10',  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
            agentCode:'10000',
            createDatetime:'',
            status:'', //0 表示正在审批当中了；1表示结束；2驳回。
            startTime:'',
            endTime:'',
            endDatetime:'',
            leaveType:'',
            leaveOff:'',
            title:'',
            imcomeproveMonth:'',
            currentGrade:'',
            agentName:'JohnnyZ'
        }
    }
    //选择照片
    selectPhotoTapped = () => {
        const options = {
            quality: 1.0,
            maxWidth: 500,
            maxHeight: 500,
            storageOptions: {
                skipBackup: true
            }

        };

        ImagePicker.showImagePicker(options, (response) => {
            console.log('Response = ', response);

            if (response.didCancel) {
                console.log('User cancelled photo picker');
            }
            else if (response.error) {
                console.log('ImagePicker Error: ', response.error);
            }
            else if (response.customButton) {
                console.log('User tapped custom button: ', response.customButton);
            }
            else {
                let source = {uri: response.uri};

                // You can also display the image using data:
                //let source = { uri: 'data:image/jpeg;base64,' + response.data };

                this.state.imageArray.push(response);
                this.setState({
                    imageNamePath: response.fileName,
                    imageDate: source,
                });
                // this.imageArray.push(response);
                console.log(this.state.imageArray);
            }
        });
    }
//删除照片
    removePhoto = (index) => {
        this.setState({
            imageArray: this.state.imageArray.filter((_, i) => i !== index)
        })
    }
    submissionForm=()=>{
        let descr = this.state.description;
        // 用途不写予以警告。
        if(descr===''){
            alert('详细说明没有编写');
            return;
        }

        let imgAry= this.state.imageArray;
        console.log('imgAry', imgAry);

        const data = [];
        imgAry.forEach(photo => {
            data.push(photo.data);
        });

        let jsonData = {
            type : this.state.type,
            description : this.state.description,
            agentCode : this.state.agentCode,
            agentName : this.state.agentName,
            files : data
        };

        this.fetchData(JSON.stringify(jsonData));
    }
    fetchData(parmars) {
        let url = RequestURL.HOST+'applyForm/saveMultipleFormBase64';
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type':'application/json;charset=utf-8',
            },
            body:parmars,
            // body: JSON.stringify({
            //     firstParam: 'yourValue',
            //     secondParam: 'yourOtherValue',
            // })
        })
            .then((response) => response.json( ))
            .then((responseJson) => {
                //alert(parmars);
                if (responseJson.code==='1'){
                    Toast.show("保存成功",Toast.LONG);
                    //DeviceEventEmitter.emit('userCenterToAdministrative'); //用户中心跳转到行政审批

                }else {
                    Toast.show("保存失败",Toast.LONG);
                }
            })
            .catch((error) => {
                console.error(error);
            });
    }
    render() {
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <ApplyCommonHeader
                    title={'万能申请'}
                    onReturn={() => this.props.navigation.goBack()}
                />
                <View style={{flexDirection:'column',paddingLeft:10,paddingRight:10,backgroundColor:'#FFFFFF',paddingTop:10
                    ,marginBottom:20,paddingBottom:20,borderColor:'#F2F2F2',borderBottomWidth:1}}>
                    <View style={{flexDirection:'column',paddingLeft:10,paddingRight:10,backgroundColor:'#FFFFFF',paddingTop:10
                        ,marginBottom:20,paddingBottom:20,borderColor:'#F2F2F2',borderBottomWidth:1}}>
                        <View style={{marginBottom:10}}>
                            <Text style={{color:'#333333',fontSize:15}}>详细说明</Text>
                        </View>
                        <View style={styles.container}>
                        <Textarea
                            containerStyle={styles.textareaContainer}
                            style={styles.textarea}
                            onChangeText={(text) => {this.setState({description:text});}}
                            defaultValue={this.state.description}
                            maxLength={120}
                            placeholder={'请输入详细说明'}
                            placeholderTextColor={'#c7c7c7'}
                            underlineColorAndroid={'transparent'}
                        />
                        </View>
                    </View>

                    <View style={{backgroundColor: 'white', marginTop: 1, alignItems: 'flex-start', paddingBottom: 15,}}>
                        <Text style={{height: 40, paddingLeft: 20, paddingTop: 10,}}>图片</Text>
                        <View style={{flexDirection: 'row',}}>
                            <View style={{
                                flexWrap: 'wrap',
                                flexDirection: 'row',
                                justifyContent: 'flex-start',
                                alignItems: 'center',
                                marginLeft: 30,
                            }}>


                                {/*<View style={{width:imageWidth,height:70,flexDirection:'row',marginRight:15,}}>*/}
                                {/*<Image style={{width:imageWidth-15,height:65,borderRadius:10,}} source={require('../../../img/Home/banner1.jpg')} />*/}
                                {/*<Image style={{width:20,height:20,marginTop:-10,marginLeft:-10,}}source={require('../../../img/UserCenter/close.png')}/>*/}
                                {/*</View>*/}

                                {this.state.imageArray.map((rowData, i) => (
                                    //<Text>{rowData.data}</Text>
                                    // console.log(rowData,i),
                                    // <Image style={styles.avatar} source={this.state.avatarSource} />
                                    // var aa = {}
                                    <View key={i} style={{width: imageWidth,height: 70,flexDirection: 'row',marginRight: 15,marginBottom: 10,justifyContent: 'center',}}>
                                        <Image style={{width: imageWidth - 15, height: 65, borderRadius: 10,}}
                                               source={{uri: 'data:'+rowData.type+';base64,' + rowData.data}}/>
                                        {/*source={{uri: 'data:image/png;base64,' + rowData.data}}/>*/}
                                        <TouchableOpacity
                                            style={{width: 20, height: 20, marginTop: -10, marginLeft: -10,}}
                                            onPress={this.removePhoto.bind(this, i)}>
                                            <Image style={{width: 20, height: 20,}}
                                                   source={require('../../../img/UserCenter/close.png')}/>
                                        </TouchableOpacity>
                                    </View>
                                ))
                                }
                                {/*{*/}
                                {/*this.state.imageArray.map((rowData, i) => (*/}
                                {/*//<Text>{rowData.data}</Text>*/}
                                {/*// console.log(rowData,i),*/}
                                {/*// <Image style={styles.avatar} source={this.state.avatarSource} />*/}
                                {/*// var aa = {}*/}
                                {/*<Image key={i} style={{width: 80, height: 80,margin:5,}} source={{uri:'data:image/png;base64,'+ rowData.data}}/>*/}
                                {/*))*/}
                                {/*}*/}


                                {this.state.imageArray.length < 9 ? (
                                    <View style={{
                                        width: imageWidth,
                                        height: 70,
                                        flexDirection: 'row',
                                        marginRight: 15,
                                        justifyContent: 'center',
                                    }}>
                                        <TouchableOpacity style={{flex: 9, justifyContent: 'center',}}
                                                          onPress={this.selectPhotoTapped}>
                                            <Image style={{width: imageWidth - 15, height: 65,}}
                                                   source={require('../../../img/UserCenter/addImage.png')}/>
                                        </TouchableOpacity>
                                    </View>
                                ) : (<View></View>)}


                                {/*<View style={{margin:10,backgroundColor:'#F2F2F2',}}>*/}
                                {/*<TouchableOpacity onPress={this.selectPhotoTapped.bind(this)}>*/}
                                {/*<Text style={{color:'#333333',fontSize:60,textAlign:'center',}}>+</Text>*/}
                                {/*</TouchableOpacity>*/}
                                {/*</View>*/}
                            </View>
                        </View>
                    </View>
                </View>

                <TouchableOpacity  onPress={this.submissionForm}>
                    <View style={{flexDirection: 'row',marginTop:80}}>
                        <Text style={{flex:0.5}}></Text>
                        <Text style={{flex:9,fontSize:20,height: 50,lineHeight:50,borderRadius:5,textAlign:'center',
                            backgroundColor:(this.state.description!=='')?'#FFDD00':'#EBEBEB'
                            ,color:(this.state.description!=='')?'#000000':'#858585'}}
                        >提交</Text>
                        <Text style={{flex:0.5}}></Text>
                    </View>
                </TouchableOpacity>
            </ScrollView>
        );
    }

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 0,
        justifyContent: 'center',
        alignItems: 'center',
        borderColor:'#F1F1F1',
        borderWidth:1
    },
    textareaContainer: {
        height: 180,
        padding: 5,
        backgroundColor: '#FFFFFF',
    },
    textarea: {
        textAlignVertical: 'top',  // hack android
        height: 170,
        fontSize: 16,
        color: '#000000',
    },
});