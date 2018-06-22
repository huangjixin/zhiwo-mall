import React from "react";
import {View,Text,TouchableWithoutFeedback,Image,ActivityIndicator,Dimensions,} from "react-native";

var ScreenWidth = Dimensions.get('window').width;
var ScreenHeight = Dimensions.get('window').height;

var indicatorPaddingLeft = ScreenWidth/2-50;
var indicatorPaddingBottom = ScreenHeight/2-50;

export class Indicator extends React.Component {
    constructor(props){
        super(props);
        const animating = (this.props.animating==null)?true:this.props.animating;
        this.state = {
            animating:animating,  //筛选model控制
        }
    }

    
    componentWillReceiveProps(nextProps){
        this.setState({ animating: nextProps.animating });
    }

    render() {
        const {
            animating
        } = this.props;

        return (
            <View style={{position:'absolute',left:indicatorPaddingLeft,bottom:indicatorPaddingBottom}}>
                <ActivityIndicator
                animating={this.state.animating}
                style={{height: 100,width:100}}
                size="large" />
            </View>
        );
    }
}