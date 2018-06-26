import React from 'react';
import {TouchableOpacity,StyleSheet,ScrollView,Text,BackHandler,Dimensions,View,Platform,Image,Button,TextInput,TouchableWithoutFeedback,TouchableHighlight,ImageBackground} from 'react-native';
import ImagePicker from 'react-native-image-picker';
import Textarea from 'react-native-textarea';
import Picker from 'react-native-picker';
import {ApplyCommonHeader} from "./ApplyCommonHeader";
import * as RequestURL from "../../common/RequestURL";
//var Fileupload = require('NativeModules').FileUpload;
var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;
const imageWidth = (Dimensions.get('window').width - 15 * 5) / 3;
const screenWidth = Dimensions.get('window').width / 3 - 60;
const paddingLeft = (Dimensions.get('window').width / 3 - screenWidth) / 2;


export class OaApplyForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            leaveTypeArry: [],//请假类型
            imageArray: [], //图片数组
            isShowPicker: false,
            showStartTimeText:'',
            showEndTimeText:'',

            id: '',
            description: '',
            type: 1,  //0离职 1请假 2晋升 3复效 4 地址 5手机号 6银行卡 7收入证明 8工作证明 9其它收入证明
            agentCode: '10000',
            createDatetime: '',
            status: '', //0 表示正在审批当中了；1表示结束；2驳回。
            startTime: '',
            endTime: '',
            endDatetime: '',
            leaveType: '',
            leaveOff: '',//时长
            title: '',
            imcomeproveMonth: '',
            currentGrade: '',
            upGrade: '',
            name: '',
            agentName: 'JohnnyZ'
        }
    }
    //开始时间选择
    _showAmPicker() {
        let years = [],
            months = [],
            days = [],
            hours = [],
            minutes = [];

        for(let i=1;i<51;i++){
            years.push(i+2000);
        }
        for(let i=1;i<13;i++){
            months.push(i);
            hours.push(i);
        }
        for(let i=1;i<32;i++){
            days.push(i);
        }
        for(let i=1;i<61;i++){
            minutes.push(i);
        }
        let pickerData = [years, months, days, ['上午', '下午']];
        let date = new Date();
        let selectedValue = [
            date.getFullYear(),
            date.getMonth()+1,
            date.getDate(),
            date.getHours() > 11 ? '上午' : '下午'
        ];
        Picker.init({
            pickerData,
            selectedValue,
            pickerTitleText: '请选择请假时间',
            pickerConfirmBtnText:'确定',
            pickerCancelBtnText:'取消',
            wheelFlex: [2, 1, 1, 2],
            onPickerConfirm: pickedValue => {
                var enTime ='';
                var cnTime ='';
                if(pickedValue[1]<10){
                    pickedValue[1]='0'+pickedValue[1];
                }
                if(pickedValue[2]<10){
                    pickedValue[2]='0'+pickedValue[2];
                }
                if(pickedValue[3]==='上午'){
                    var enTime = pickedValue[0] + '-' + pickedValue[1] + '-' + pickedValue[2]+' 00:00:00';
                    var cnTime = pickedValue[0] + '-' + pickedValue[1] + '-' + pickedValue[2]+' 上午';
                }else {
                    var enTime = pickedValue[0] + '-' + pickedValue[1] + '-' + pickedValue[2]+' 12:00:00';
                    var cnTime = pickedValue[0] + '-' + pickedValue[1] + '-' + pickedValue[2]+' 下午';
                }

                if (this.state.showEndTimeText !== '') {
                    if (!((new Date(enTime.replace(/-/g, "\/"))) <= (new Date(this.state.endTime.replace(/-/g, "\/"))))) {
                        alert('开始时间必须早于结束时间');
                        return;
                    }
                    this.setState({
                        leaveOff: this.computeDaysDelta(enTime, this.state.endTime),
                    })
                }
                this.setState({
                    showStartTimeText:cnTime,
                    startTime: enTime,
                })
                console.log('area', pickedValue);
            },
            onPickerCancel: pickedValue => {
                console.log('area', pickedValue);
            },
            onPickerSelect: pickedValue => {
                let targetValue = [...pickedValue];
                if(parseInt(targetValue[1]) === 2){
                    if(targetValue[0]%4 === 0 && targetValue[2] > 29){
                        targetValue[2] = 29;
                    }
                    else if(targetValue[0]%4 !== 0 && targetValue[2] > 28){
                        targetValue[2] = 28;
                    }
                }
                else if(targetValue[1] in {4:1, 6:1, 9:1, 11:1} && targetValue[2] > 30){
                    targetValue[2] = 30;

                }
                // forbidden some value such as some 2.29, 4.31, 6.31...
                if(JSON.stringify(targetValue) !== JSON.stringify(pickedValue)){
                    // android will return String all the time，but we put Number into picker at first
                    // so we need to convert them to Number again
                    targetValue.map((v, k) => {
                        if(k !== 3){
                            targetValue[k] = parseInt(v);
                        }
                    });
                    Picker.select(targetValue);
                    pickedValue = targetValue;
                }
            }
        });
        Picker.show();
    }
    //结束时间选择
    _showPmPicker() {
        let years = [],
            months = [],
            days = [],
            hours = [],
            minutes = [];

        for(let i=1;i<51;i++){
            years.push(i+2000);
        }
        for(let i=1;i<13;i++){
            months.push(i);
            hours.push(i);
        }
        for(let i=1;i<32;i++){
            days.push(i);
        }
        for(let i=1;i<61;i++){
            minutes.push(i);
        }
        let pickerData = [years, months, days, ['上午', '下午']];
        let date = new Date();
        let selectedValue = [
            date.getFullYear(),
            date.getMonth()+1,
            date.getDate(),
            date.getHours() > 11 ? '上午' : '下午'
        ];
        Picker.init({
            pickerData,
            selectedValue,
            pickerTitleText: '请选择请假时间',
            pickerConfirmBtnText:'确定',
            pickerCancelBtnText:'取消',
            wheelFlex: [2, 1, 1, 2],
            onPickerConfirm: pickedValue => {
                var enTime ='';
                var cnTime ='';
                if(pickedValue[1]<10){
                    pickedValue[1]='0'+pickedValue[1];
                }
                if(pickedValue[2]<10){
                    pickedValue[2]='0'+pickedValue[2];
                }
                if(pickedValue[3]==='上午'){
                    var enTime = pickedValue[0] + '-' + pickedValue[1] + '-' + pickedValue[2]+' 00:00:00';
                    console.log(enTime);
                    var cnTime = pickedValue[0] + '-' + pickedValue[1] + '-' + pickedValue[2]+' 上午';
                }else {
                    var enTime = pickedValue[0] + '-' + pickedValue[1] + '-' + pickedValue[2]+' 12:00:00';
                    var cnTime = pickedValue[0] + '-' + pickedValue[1] + '-' + pickedValue[2]+' 下午';
                }

                if (this.state.showStartTimeText !== '') {
                    if (!((new Date(this.state.startTime.replace(/-/g, "\/"))) <= (new Date(enTime.replace(/-/g, "\/"))))) {
                        alert('结束时间必须晚于开始时间');
                        return;
                    }
                    this.setState({
                        leaveOff: this.computeDaysDelta(this.state.startTime,enTime),
                    })
                }
                this.setState({
                    showEndTimeText:cnTime,
                    endTime: enTime,
                })
                console.log('area', pickedValue);
            },
            onPickerCancel: pickedValue => {
                console.log('area', pickedValue);
            },
            onPickerSelect: pickedValue => {
                let targetValue = [...pickedValue];
                if(parseInt(targetValue[1]) === 2){
                    if(targetValue[0]%4 === 0 && targetValue[2] > 29){
                        targetValue[2] = 29;
                    }
                    else if(targetValue[0]%4 !== 0 && targetValue[2] > 28){
                        targetValue[2] = 28;
                    }
                }
                else if(targetValue[1] in {4:1, 6:1, 9:1, 11:1} && targetValue[2] > 30){
                    targetValue[2] = 30;

                }
                // forbidden some value such as some 2.29, 4.31, 6.31...
                if(JSON.stringify(targetValue) !== JSON.stringify(pickedValue)){
                    // android will return String all the time，but we put Number into picker at first
                    // so we need to convert them to Number again
                    targetValue.map((v, k) => {
                        if(k !== 3){
                            targetValue[k] = parseInt(v);
                        }
                    });
                    Picker.select(targetValue);
                    pickedValue = targetValue;
                }
            }
        });
        Picker.show();
    }

    //时间转换
    convertStringToDate(dateString) {
        dateStr = dateString.split(' ');
        dateStr2 = dateStr[1].split(':');
        dateString1 = dateStr[0].split('-');
        return new Date(dateString1[0], dateString1[1] - 1, dateString1[2],dateStr2[0]);
    }
//计算时长
    computeDaysDelta(date1, date2) {
        date1 = this.convertStringToDate(date1);
        date2 = this.convertStringToDate(date2);
        delta = (date2 - date1) / (1000 * 60 * 60 * 12) + 1;

        weekEnds = 0;
        for (i = 0; i < delta; i++) {
            if (date1.getDay() == 0 || date1.getDay() == 6) weekEnds++;
            date1 = date1.valueOf();
            date1 += 1000 * 60 * 60 * 12;
            date1 = new Date(date1);
        }
        return (delta - weekEnds)/2;
    }
//选择请假类型
    _showOaApplyPicker() {
        Picker.init({
            pickerData: ['病假', '婚假', '产假', '陪产假', '丧假'],
            pickerFontColor: [0, 0, 0, 1],
            pickerConfirmBtnText: '确定',
            pickerCancelBtnText: '取消',
            pickerTitleText: '请选择请假类型',
            onPickerConfirm: (pickedValue, pickedIndex) => {
                this.setState({
                    leaveTypeArry: [pickedValue[0], pickedIndex[0]],
                    leaveType:pickedIndex[0],
                })
                //console.log('date', pickedValue[0], pickedIndex,this.state.leaveTypeArry);
                // alert(pickedValue[0]+'----'+pickedValue[1]);
                // console.log('date', pickedValue, pickedIndex);
            },
            // onPickerCancel: (pickedValue, pickedIndex) => {
            //     console.log('date', pickedValue, pickedIndex);
            // },
            // onPickerSelect: (pickedValue, pickedIndex) => {
            //     console.log('date', pickedValue, pickedIndex);
            // }
        });
        Picker.show();
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
    //提交数据
    submitData = () => {
        this.upload1();
        return;
        if (this.state.leaveTypeArry.length == 0) {
            alert('请选择请假类型');
            return;
        }
        if (this.state.showStartTimeText == '') {
            alert('请选择起始时间');
            return;
        }
        if (this.state.showEndTimeText == '') {
            alert('请选择结束时间');
            return;
        }
        if (this.state.description.length > 1000) {
            alert('描述不得大于1000');
            return;
        }

        if ((!this.state.leaveOff == 1 && this.state.leaveTypeArry[1] == 0)) {
            // 没有强制要求上传图片。
            // if (this.state.imageArray.length == 0) {
            //     alert('请上传图片');
            //     return;
            // }
        }




        let imgAry= this.state.imageArray;
        console.log('imgAry', imgAry);
        let fileData = new FormData();  

     //因为需要上传多张图片,所以需要遍历数组,把图片的路径数组放入formData中
        var i = 0;
        // for(i = 0;i<imgAry.length;i++){
        
        // //截取获取文件名
        //     var a=imgAry[i].uri;
        //     var arr = a.split('/');
        // // 获取文件名end
        // //      判断文件的类型(视频-图片等)end
        //     let file = {uri: imgAry[i], type: imgAry[i].mime, name: arr[arr.length-1]};   //这里的key(uri和type和name)不能改变,
        //     //formData.append("file", file);   //这里的files就是后台需要的key

        //     fileData.append("file", file);   //这里的files就是后台需要的key
        //     //这里的files就是后台需要的key
        // }

        //先上传第一个文件。
        if(imgAry.length>0){
            let obj = imgAry[0];
            let file = {uri: obj.path, type: obj.type, name: obj.fileName};
            fileData.append("file", file);
        }
        // let file = {uri: params.path, type: 'application/octet-stream', name: 'image.jpg'};
        // formData.append("file", file);

        let formData = new FormData();
        formData.append("type", this.state.type);
        formData.append("leaveType", this.state.leaveType);
        formData.append("leaveOff", this.state.leaveOff);
        formData.append("startTime", this.state.startTime);
        formData.append("endTime", this.state.endTime);
        formData.append("description", this.state.description);
        formData.append("agentCode", this.state.agentCode);
        formData.append("agentName", this.state.agentName);
        if(this.state.imageArray.length>0){
            formData.append("file", fileData);
        }
        
        this.fetchData(formData);
    }


    // 单独测试提交一个文件到后台
    upload=()=>{
        let file = {uri:this.state.imageArray[0].uri,
         type:this.state.imageArray[0].type, name:this.state.imageArray[0].fileName};
        
        let formData = new FormData();
        formData.append("type", this.state.type);
        formData.append("agentCode", this.state.agentCode);
        formData.append("name", this.state.name);
        let fileData = new FormData(); 
        // fileData.append("file", file);
        formData.append("file", file);

        //  Kane哥，后面这么写，搞一个变量，调试的时候也容易。   
        let url = RequestURL.HOST+'applyForm/upload';
        url = 'http://10.23.21.120:50000/applyForm/upload';
        formData.append("file", file);
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'multipart/form-data;charset=utf-8',
            },
            body: formData,
        }).then((response) => {})
            .then((responseData)=> {
               
            })
            .catch((err)=> {
                console.log('err', err);
            });

    }

    // 单独测试提交一个文件到后台
    upload1=()=>{
        var a =[];
        let fileData = new FormData();
        for (var i =0;i<this.state.imageArray.length;i++){
            let file = 'data:image/jpeg;base64,'+this.state.imageArray[i].data;
            // let aa = [];
            a.push(file);
            // fileData.append("file", file);
            //formData.append("file", file);
        }
        let formData = new FormData();
        formData.append("type", this.state.type);
        formData.append("agentCode", this.state.agentCode);
        formData.append("name", this.state.agentName);
        // let fileData = new FormData();
        // fileData.append("file", file);
        // formData.append("file", file);

        let url = RequestURL.HOST+'applyForm/saveForm';
        formData.append("files", a);
        fetch(url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: formData,
        }).then((response) => {})
            .then((responseData)=> {

            })
            .catch((err)=> {
                console.log('err', err);
            });

    }


    // 提交数据到后台
    fetchData(parmars) {
         //  Kane哥，后面这么写，搞一个变量，调试的时候也容易。   
        let url = RequestURL.HOST+'applyForm/saveSingleForm';

        fetch(url, {
            method: 'POST',
            headers: {
            'Content-Type':'multipart/form-data;charset=utf-8',
            },
            body: parmars,
        })
            .then((response) => response.json())
            .then((responseJson) => {
                if (responseJson === 1) {
                    DeviceEventEmitter.emit('userCenterToAdministrative'); //用户中心跳转到行政审批
                }
            })
            .catch((error) => {
                console.error(error);
            });
    }

    componentWillMount() {
        if (Platform.OS === 'android') {
            BackHandler.addEventListener('hardwareBackPress', this.onBackAndroid);
        }
    }

    componentWillUnmount() {
        if (Platform.OS === 'android') {
            BackHandler.removeEventListener('hardwareBackPress', this.onBackAndroid);
        }
    }
//处理安卓物理按键时picker回收
    onBackAndroid = () => {
        Picker.isPickerShow(status => {
            if (status) {
                Picker.hide();
                return true;
            } else {
                this.props.navigation.goBack();
                return false;
            }
        });
        return true;
    };

    render() {
        return (
            <ScrollView style={{backgroundColor: '#FBFBFB',}}>
                <ApplyCommonHeader title={'请假申请'} onReturn={() => this.props.navigation.goBack()}/>

                <View style={{flexDirection: 'row', height: 40, backgroundColor: 'white', alignItems: 'center',}}>
                    <Text style={{flex: 3, paddingLeft: 20, fontSize: 16,}}>请假类型</Text>
                    <TouchableOpacity style={{height: 40, flex: 7,}} onPress={this._showOaApplyPicker.bind(this)}>
                        <View style={{flexDirection: 'row', alignItems: 'center',}}>
                            <Text style={{
                                flex: 9,
                                textAlign: 'right',
                                paddingRight: 15,
                                fontSize: 16,
                            }}>{this.state.leaveTypeArry[0]}</Text>
                            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center', paddingTop: 12,}}>
                                <Image style={{width: 10, height: 16, marginRight: 20,}}
                                       source={require('../../../img/UserCenter/rightArr.png')}/>
                            </View>
                        </View>
                    </TouchableOpacity>
                </View>
                <View style={{
                    flexDirection: 'row',
                    height: 40,
                    backgroundColor: 'white',
                    marginTop: 1,
                    alignItems: 'center',
                }}>
                    <Text style={{flex: 3, paddingLeft: 20, fontSize: 16,}}>开始时间</Text>
                    <TouchableOpacity style={{flex: 7,}} onPress={this._showAmPicker.bind(this)}>
                        <View style={{flexDirection: 'row', alignItems: 'center',}}>
                            {this.state.showStartTimeText == '' ? (
                                <Text style={{flex: 9, textAlign: 'right', paddingRight: 15, fontSize: 16,}}>请选择</Text>
                            ) : (<Text style={{
                                    flex: 9,
                                    textAlign: 'right',
                                    paddingRight: 15,
                                    fontSize: 16,
                                }}>{this.state.showStartTimeText}</Text>
                            )}
                            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center',}}>
                                <Image style={{width: 10, height: 16, marginRight: 20,}}
                                       source={require('../../../img/UserCenter/rightArr.png')}/>
                            </View>
                        </View>
                    </TouchableOpacity>
                </View>
                <View style={{
                    flexDirection: 'row',
                    height: 40,
                    backgroundColor: 'white',
                    marginTop: 1,
                    alignItems: 'center',
                }}>
                    <Text style={{flex: 3, paddingLeft: 20, fontSize: 16,}}>结束时间</Text>
                    <TouchableOpacity style={{flex: 7,}} onPress={this._showPmPicker.bind(this)}>
                        <View style={{flexDirection: 'row', flex: 7, alignItems: 'center',}}>
                            {this.state.showEndTimeText === '' ? (
                                <Text style={{
                                    flex: 9,
                                    textAlign: 'right',
                                    paddingRight: 15,
                                    fontSize: 16,
                                }}>请选择</Text>) : (
                                <Text style={{
                                    flex: 9,
                                    textAlign: 'right',
                                    paddingRight: 15,
                                    fontSize: 16,
                                }}>{this.state.showEndTimeText}</Text>)}
                            <View style={{flex: 1, alignItems: 'center', justifyContent: 'center',}}>
                                <Image style={{width: 10, height: 16, marginRight: 20,}}
                                       source={require('../../../img/UserCenter/rightArr.png')}/>
                            </View>
                        </View>
                    </TouchableOpacity>
                </View>

                <View style={{
                    flexDirection: 'row',
                    height: 40,
                    backgroundColor: 'white',
                    marginTop: 1,
                    alignItems: 'center',
                }}>
                    <Text style={{flex: 3, paddingLeft: 20, fontSize: 16, textAlign: 'left',}}>天数</Text>

                    <View style={{flex: 7, flexDirection: 'row', alignItems: 'center',}}>
                        <Text style={{
                            flex: 8.5,
                            textAlign: 'right',
                            paddingRight: 15,
                            fontSize: 16,
                        }}>{this.state.leaveOff}</Text>
                        <View style={{flex: 1.5, alignItems: 'center', justifyContent: 'center',}}>
                            <Text style={{fontSize: 16, marginRight: 20,}}>天</Text>
                        </View>
                    </View>
                </View>

                <View style={{backgroundColor: 'white', alignItems: 'flex-start', marginTop: 1,}}>
                    <Text style={{height: 40, paddingLeft: 20, paddingTop: 10, fontSize: 16,}}>详细说明</Text>
                    <View style={{
                        flexDirection: 'row',
                        justifyContent: 'space-between',
                        paddingTop: 10,
                        paddingBottom: 20,
                        marginRight: 20,
                        marginLeft: 20,
                    }}>
                        <Textarea
                            containerStyle={styles.textareaContainer}
                            style={styles.textarea}
                            onChangeText={(text) => {
                                this.setState({description: text});
                            }}
                            defaultValue={this.state.description}
                            maxLength={1000}
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
                                               source={{uri: 'data:image/png;base64,' + rowData.data}}/>
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

                <View style={{flexDirection: 'row', marginTop: 40, marginBottom: 40,}}>
                    <Text style={{flex: 0.5}}></Text>
                    <TouchableOpacity style={{flex: 9}} onPress={this.submitData}>
                        <Text style={{flex: 6,fontSize: 20,height: 50,lineHeight: 50,borderRadius: 5,textAlign: 'center',fontWeight: '900',
                            backgroundColor: ((this.state.leaveTypeArry.length !== 0) && (this.state.showStartTimeText !== '') && (this.state.leaveTypeArry.showEndTimeText !== '') && (this.state.leaveOff !== '')) ? '#FFDD00' : '#EBEBEB',
                            color: ((this.state.leaveTypeArry.length !== 0) && (this.state.showStartTimeText !== '') && (this.state.leaveTypeArry.showEndTimeText !== '') && (this.state.leaveOff !== '')) ? '#000000' : '#858585',}}
                        >提交</Text>
                    </TouchableOpacity>

                    <Text style={{flex: 0.5}}></Text>
                </View>
            </ScrollView>
        );
    }
}

const styles = StyleSheet.create({
    textareaContainer: {
        height: 180,
        paddingTop: 5,
        paddingBottom: 5,
        backgroundColor: '#FFFFFF',
        borderColor: '#F1F1F1',
        borderWidth: 1
    },
    textarea: {
        textAlignVertical: 'top',  // hack android
        height: 170,
        fontSize: 16,
        color: '#000000',
    }

});