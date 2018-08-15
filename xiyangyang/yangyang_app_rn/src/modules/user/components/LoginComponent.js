import React from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import LoginAction from '../actions/LoginAction';
import { StyleSheet, Text, View,Button } from 'react-native';

export class LoginComponent extends React.Component {
 
  constructor(props) {
    super(props);
  }

  // 登录
  login = ()=>{
    console.dir(this.props);
    this.props.actions.login();
  }

  render() {
    // let status = this.props.status;

    return (
      <View style={styles.container}>
        <Text>{this.props.status}</Text>
        <Button onPress={this.login} title="登录"></Button>
        <Text>版权所有</Text>
      </View>
    );
  }
}


const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    },
  });

  //根据全局state返回当前页面所需要的信息,（注意以props的形式传递给Login）
const mapStateToProps = (state)=>{
    const status = state.loginReducer.status;
    return{
        status:status
    }
}

//返回可以操作store.state的actions,(其实就是我们可以通过actions来调用我们绑定好的一系列方法)
const mapDispatchToProps = (dispatch)=>{
    return {
        actions: bindActionCreators(LoginAction, dispatch)
    };
}  

export default connect(mapStateToProps,mapDispatchToProps)(LoginComponent);