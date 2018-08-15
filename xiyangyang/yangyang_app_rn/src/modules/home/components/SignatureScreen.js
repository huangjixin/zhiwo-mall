import React from 'react';
import {
  Image,
  ActivityIndicator,
  AsyncStorage,
  StyleSheet,
  View,Text,Button,Dimensions,PanResponder,TouchableOpacity,ScrollView,ART
} from 'react-native';
import { BaseScreenComponent } from '../../../core/BaseScreenComponent';
import ScreenUtil from '../../../utils/ScreenUtil';
// import { captureRef } from "react-native-view-shot";


const {
  Shape,
  Surface,
  Group,
  Path
} = ART
//获取屏幕的宽高, 
const width = Dimensions.get('screen').width;
const height = Dimensions.get('screen').height;
/**
 * 签名
 */
export class SignatureScreen extends BaseScreenComponent {
  
  constructor(props) {
    super(props);
    this.state = {
      //用于更新界面
      lastX: 0,
      uri:null,
    };

    //每次移动的临时数组
    this.MousePostion = {
        firstX:0, //起点 X 坐标
        firstY:0,// 起点 Y 坐标
        x: 0,   //经过路径的x坐标
        y: 0    //经过路径的y坐标
    }
    //path 全部路径数组
    this.MousePostions = []
  }

  // 清除
  clearHandler=()=>{
    this.MousePostions = [];
    this.setState({
      //用于更新界面
      lastX: 0,
    })
  }
  
  // 保存图片，加载图片
  saveHandler=(refname)=>{
    // captureRef(refname, {
    //   format: "jpg",
    //   quality: 0.8,
    //   result: "tmpfile",
    //   snapshotContentContainer: true
    // })
    // .then(
    //   uri => console.log("Image saved to", uri),
    //   error => console.error("Oops, snapshot failed", error)
    // );
    // ReactNative.takeSnapshot(this.refs.location, {format: 'png', quality: 1}).then(
    //     (uri) => this.setState({uri:uri})
    // ).catch(
    //     // (error) => alert(error)
    // );
  }

  componentWillMount() {
    this._panResponder = PanResponder.create({
        onStartShouldSetPanResponder: (evt, gestureState) => {
            return true;
        },
        onMoveShouldSetPanResponder: (evt, gestureState) => {
            return true;
        },
        onPanResponderGrant: (evt, gestureState) => {
            //手指按下时的画笔起点坐标
            this.tempfirstX = evt.nativeEvent.pageX
            this.tempFirstY = evt.nativeEvent.pageY-40-(ScreenUtil.isIphoneX() == true?30:0);
        },//激活时做的动作
        onPanResponderMove: (evt, gestureState) => {
            this.MousePostion = {
                firstX:this.tempfirstX,
                firstY:this.tempFirstY,
                x: this.tempfirstX + gestureState.dx,
                y: this.tempFirstY + gestureState.dy
            }
            this.MousePostions.push(this.MousePostion);

            //更新界面
            this.setState({
                lastX: this.MousePostions[0].x + gestureState.dx,
            })


        }, //移动时作出的动作

        onPanResponderRelease: (evt, gestureState) => {
        },///动作释放后做的动作

        onPanResponderTerminate: (evt, gestureState) => {
        },
    });

}
  // Render any loading content that you like here
  render() {
    const path = new Path();
    for (let i = 0; i < this.MousePostions.length; i++) {
      let tempFistX = this.MousePostions[i].firstX
      let tempFistY = this.MousePostions[i].firstY
      let tempX = this.MousePostions[i].x
      let tempY = this.MousePostions[i].y
      if (i == 0) {
          path.moveTo(tempFistX, tempFistY)
          path.lineTo(tempX, tempY)
          path.close();
      } else {
          let tempFistX_1 = this.MousePostions[i-1].firstX

          if(tempFistX==tempFistX_1){
              let tempX_1 = this.MousePostions[i - 1].x
              let tempY_1 = this.MousePostions[i - 1].y
              path.moveTo(tempX_1, tempY_1)
              path.lineTo(tempX, tempY)
              path.close();
          }else {
              path.moveTo(tempFistX, tempFistY)
              path.lineTo(tempX, tempY)
              path.close();
          }
      }
  } 
  
    return (
      <View style={styles.container}>
        <View style={{flexDirection:'row'}}>
          <TouchableOpacity
              onPress={this.clearHandler}>
            <View style={{width: width/2, height: 40, backgroundColor: 'red',justifyContent:'center',alignItems: 'center'}}>
              <Text style={{color: '#ffffff',fontSize:16}}>清除</Text>
            </View>
          </TouchableOpacity>
          <TouchableOpacity
              onPress={this.saveHandler('location')}>
            <View style={{width: width/2, height: 40, backgroundColor: 'red',justifyContent:'center',alignItems: 'center'}}>
              <Text style={{color: '#ffffff',fontSize:16}}>保存</Text>
            </View>
          </TouchableOpacity>
        </View>
        <View  ref='location' style={styles.container} {...this._panResponder.panHandlers} >
          <Surface width={width} height={height-100}>
              <Group>
                <Shape d={path} stroke="#000000" strokeWidth={1}/>
              </Group>
          </Surface>
        </View>
        {/* <Image style={{borderWidth: 1, height: (height-40), width: width, marginTop: 10}} source={{uri: this.state.uri}} /> */}
      </View>
    );
  }
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      // backgroundColor: '#fff',
      // alignItems: 'center',
      // justifyContent: 'center',
    },
  });