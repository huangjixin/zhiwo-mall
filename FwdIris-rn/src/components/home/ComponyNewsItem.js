import React from 'react';
import {StyleSheet, ScrollView,View, Text,Image,TouchableOpacity} from 'react-native';

export class ComponyNewsItem extends React.Component {

    render() {
        const {title} = this.props;
        return (

            <TouchableOpacity  onPress={this.props.onPress}>
                <View style={{paddingBottom:5,borderBottomWidth:1,borderBottomColor:'#CCCCCC',flex:1, flexDirection: 'row',justifyContent: 'flex-start',marginTop:10}}>
                    <View style={{flex: 3}}>
                        <Text style={{fontSize:16}}>{title}</Text>
                        <Text style={{height:20}}></Text>
                        <Text>200 人阅读         2018/01/01 08:00</Text>
                    </View>
                    <View style={{flex: 1,marginLeft:5}}>
                        <Image source={{uri: 'http://omsproductionimg.yangkeduo.com/images/2017-11-16/164402b57a195acee7e3144fd88ce46c.jpeg'}}
                               style={{width: 80, height:80}} />
                    </View>
                </View>
            </TouchableOpacity>


        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
});

