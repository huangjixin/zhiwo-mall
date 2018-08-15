import React from 'react';
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';

import {
  ActivityIndicator,
  AsyncStorage,
  StyleSheet,
  View,Text,Button,ScrollView,FlatList,Image,Dimensions
} from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
import { BaseScreenComponent } from '../../../core/BaseScreenComponent';
import IndexAction from '../../index/actions/IndexAction';

const imageWidth = Dimensions.get('screen').width-20;
const imageHeight = imageWidth*168/360;

export class Test1Screen extends BaseScreenComponent {
  
  constructor(props) {
    super(props);
  }

  componentDidMount(){
    // let response = new Response();
    // console.dir(this.props.response);
    this.props.actions.getIndexData(this.props.response);
  }

  componentDidUpdate(){
    // console.dir(this.props.response.dataList)
  }

  render() {
    return (
      // <ScrollView>
          
      // </ScrollView>
      <View style={styles.container}>
        <FlatList
          data={this.props.response.dataList}
          renderItem={({item}) =>
            <View style={{flex:1,padding:10}}>
              <Image style={{width:imageWidth,height:imageHeight}} 
                    source={{uri:item.url}}></Image>
              <Text style={{color:'#000000',fontSize:16,margin:10}}>{item.title}</Text>
              <View style={{flex:1,height:10,backgroundColor:'#F4F4F4'}}></View>
            </View>
          }
        />
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

  
  //根据全局state返回当前页面所需要的信息,（注意以props的形式传递给Login）
const mapStateToProps = (state)=>{
  const {response} = state.indexReducer;
  // console.dir(response)
  return{
    response:response
  }
}

//返回可以操作store.state的actions,(其实就是我们可以通过actions来调用我们绑定好的一系列方法)
const mapDispatchToProps = (dispatch)=>{
  return {
      actions: bindActionCreators(IndexAction, dispatch)
  };
}  

export default connect(mapStateToProps,mapDispatchToProps)(Test1Screen);