
import React from "react";
import {View,Text,TouchableWithoutFeedback,Image} from "react-native";

export class ApplyCommonHeader extends React.Component {
    static defaultProps = {
        onAction : function () {},
        onReturn : function () {},
    };

    render() {
        const {
            title,
            onReturn,
            actionName,
            onAction
        } = this.props;

        return (
                <View style={{height:50,backgroundColor:'#FFDD00'
                    ,flexDirection:'row',justifyContent: 'center',alignItems: 'center'}}>
                    <TouchableWithoutFeedback onPress={onReturn}>
                        <View style={{flex:1,paddingLeft:10}}>
                                <Image style={{width:11,height:18,}}source={require('../../../img/UserCenter/GoBackBlack.png')}/>
                        </View>
                    </TouchableWithoutFeedback>
                    <View style={{flex:1,justifyContent: 'center',alignItems: 'center',}}>
                        <Text style={{color:'#000000',fontSize:22}}>{title}</Text>
                    </View>
                    <TouchableWithoutFeedback onPress={onAction}>
                        <View style={{flex:1,alignItems: 'flex-end',paddingRight:10}}>
                            <Text style={{color:'#000000',fontSize:14}}>{actionName}</Text>
                        </View>
                    </TouchableWithoutFeedback>
                </View>
        );
    }
}