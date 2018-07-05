import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image,TextInput,TouchableWithoutFeedback,Button,TouchableOpacity,Dimensions } from 'react-native';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import Textarea from 'react-native-textarea';
import Picker from 'react-native-picker';
import ImagePicker from 'react-native-image-picker';
import Toast from "./Toast/Toast";
import * as RequestURL from "../../common/RequestURL";
const imageWidth = (Dimensions.get('window').width - 15 * 5) / 3;
export class OtherProve extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            imageArray: [], //图片数组
            type:'9',
            purpose:'',
            materialsType:'',
            agentCode:'10000',
            agentName:'JohnnyZ',
            submitIndicator:false
        }
    }

    checkSubmitForm = ()=>{
        const {purpose,materialsType,submitIndicator } = this.state;
        if(materialsType.length!=0 && purpose.length!=0 ){
            if(!submitIndicator)
                this.setState({
                    submitIndicator:true
                })
        }else if(submitIndicator){
            this.setState({
                submitIndicator:false
            })
        }
    }

    onInputChange = (text)=>{
        this.state.purpose = text;
        this.checkSubmitForm();
    }

    onSubmit= ()=>{
        if(this.state.submitIndicator){
            let descr = this.state.purpose;
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
                description : this.state.purpose,
                agentCode : this.state.agentCode,
                agentName : this.state.agentName,
                files : data
            };

            this.fetchData(JSON.stringify(jsonData));
        }
    }

    fetchData(parmars) {
        let url = RequestURL.APPLY_SAVE_MULTIPLE_FORM_BASE64;
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type':'application/json;charset=utf-8',
            },
            body:parmars
        })
            .then((response) => response.json( ))
            .then((responseJson) => {
                if (responseJson.code==='1'){
                    Toast.show("保存成功",Toast.LONG);
                }else {
                    Toast.show("保存失败",Toast.LONG);
                }
            })
            .catch((error) => {
                console.error(error);
            });
    }

    _showMaterialsPicker() {
        Picker.init({
            pickerData: ['工作经历证明','学历证明','其他'],
            pickerFontColor: [0, 0 ,0, 1],
            pickerConfirmBtnText:'确定',
            pickerCancelBtnText:'取消',
            pickerTitleText:'请选择材料类型',
            onPickerConfirm: (pickedValue, pickedIndex) => {
                console.log(pickedValue[0]);
                console.log(pickedIndex[0]);
                this.setState({
                    materialsType:pickedValue[0],
                })
                this.checkSubmitForm();
            },

        });
        Picker.show();
    }
//删除照片
    removePhoto = (index) => {
        this.setState({
            imageArray: this.state.imageArray.filter((_, i) => i !== index)
        })
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



    render() {
        const submitIndicator = this.state.submitIndicator;
        return (
            <ScrollView style={{backgroundColor:'#FBFBFB'}}>
                <ApplyCommonHeader
                    title={'其他证明'}
                    onReturn={() => this.props.navigation.goBack()}
                />
                <View style={{flexDirection:'column',backgroundColor:'#FFFFFF',paddingTop:10
                    ,marginBottom:20,paddingBottom:20,borderColor:'#F2F2F2',borderBottomWidth:1}}>

                    <View style={{flexDirection:'row',borderColor:'#F2F2F2',borderBottomWidth:1,
                        backgroundColor:'#FFFFFF',height:45,justifyContent: 'center',alignItems: 'center'}}>
                        <View style={{flex:2}}>
                            <Text style={{color:'#333333',fontSize:16,paddingLeft:15,}}>材料类型</Text>
                        </View>
                        <View style={{flex:8,flexDirection:'row',paddingRight:10,}}>
                            <TouchableOpacity style={{height:40,flex:7,}} onPress={this._showMaterialsPicker.bind(this)}>
                                {this.state.materialsType.length==0?(
                                    <Text style={{flex:9,textAlign:'right',paddingRight:15,fontSize:16,lineHeight:40,}}>请选择</Text>
                                ):(
                                    <Text style={{flex:9,textAlign:'right',paddingRight:15,fontSize:16,lineHeight:40,}}>{this.state.materialsType}</Text>
                                )}
                            </TouchableOpacity>
                            <View  style={{flex:1,alignItems:'center',justifyContent:'center',}}>
                                <Image style={{width: 10, height: 16,marginRight:20,}} source={require('../../../img/UserCenter/rightArr.png')} />
                            </View>
                        </View>
                    </View>

                    <View style={{marginBottom:10,marginTop:10}}>
                        <Text style={{color:'#333333',fontSize:16,paddingLeft:15,}}>用途</Text>
                    </View>
                    <View style={styles.container}>
                        <Textarea
                                containerStyle={styles.textareaContainer}
                                style={styles.textarea}
                                onChangeText={this.onInputChange}
                                defaultValue={this.state.purpose}
                                maxLength={120}
                                placeholder={'请输入详细说明'}
                                placeholderTextColor={'#c7c7c7'}
                                underlineColorAndroid={'transparent'}
                            />
                    </View>
                    <View style={{marginTop:10,marginBottom:10,}}>
                        <Text style={{color:'#333333',fontSize:16,paddingLeft:15,}}>图片</Text>
                    </View>










                    <View style={{flexDirection: 'row',}}>
                        <View style={{
                            flexWrap: 'wrap',
                            flexDirection: 'row',
                            justifyContent: 'flex-start',
                            alignItems: 'center',
                            marginLeft: 30,
                        }}>
                            {this.state.imageArray.map((rowData, i) => (
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
                        </View>
                    </View>













                </View>

                <TouchableWithoutFeedback onPress={this.onSubmit}>
                    <View style={[{justifyContent: 'center',alignItems: 'center',backgroundColor:'#FFFFFF',height:50
                        ,borderColor:'#F2F2F2',borderBottomWidth:1,borderTopWidth:1},
                        submitIndicator?{backgroundColor:'#FFDD00'}:{backgroundColor:'#FFFFFF'}
                    ]}>
                        <Text style={{color:'#E87722',fontSize:16}}>提交</Text>
                    </View>
                </TouchableWithoutFeedback>
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
      borderWidth:1,
        marginRight:15,marginLeft:15,
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