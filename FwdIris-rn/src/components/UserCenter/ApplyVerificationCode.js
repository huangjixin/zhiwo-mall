
import React from "react";
import {View, Text, TouchableWithoutFeedback, Image, StyleSheet} from "react-native";

export class ApplyVerificationCode extends React.Component {

    timer;

    static defaultProps = {
        onClick : ()=>{},
        style : {},
        defaultCountdown:60
    };

    constructor(props){
        super(props)
        this.state = {
            countdown : -1
        };
    }

    componentWillUnmount() {
        this.timer && clearInterval(this.timer);
    }

    startTiming = (defaultCountdown)=>{
        if(this.timer){
            return;
            //clearInterval(this.timer);
        }

        this.setState({countdown : defaultCountdown});
        this.timer = setInterval(() => {
            const curVal = this.state.countdown -1;
            this.setState({countdown : curVal});
            if(curVal==-1){
                clearInterval(this.timer);
                this.timer = null;
            }
        }, 1000);
    }

    render() {
        const {
            style,
            onClick,
            defaultCountdown
        } = this.props;

        const {countdown} = this.state;
        let displayVal = null;
        if(countdown == -1){
            displayVal = "获取验证码";
        }else{
            displayVal = "重新获取(" + countdown + ")"
        }
        return (
            <TouchableWithoutFeedback onPress={()=>{
                onClick();
                this.startTiming(defaultCountdown);
            }}>
                <View style={[styles.container,style]}>
                        <Text  style={{color:'#E87722',fontSize:13}}>
                            {displayVal}
                        </Text>
                </View>
            </TouchableWithoutFeedback>
        );
    }
}

const styles = StyleSheet.create({
    container :{
        justifyContent:'center',
        alignItems: 'center',
        backgroundColor:'#FFFFFF',
        borderWidth: 1,
        borderColor: '#E87722',
        borderRadius:20,
        height:35,
        paddingBottom:10,
        paddingTop:10,
        paddingLeft:15,
        paddingRight:15
    },

});