import React from 'react';
import {StyleSheet, Image, TouchableOpacity,View, Text, ScrollView, HTMLView,BackHandler} from 'react-native';

export class NewsDetail extends React.Component {
    constructor () {
        super();
        //1.初始化state
        this.state = {
            title: '123',
        }
    }
        
      componentWillUnmount() {
        // BackHandler.removeEventListener('hardwareBackPress', this.onBackButtonPressAndroid);
      }
    
      onBackButtonPressAndroid = () => {
         // return false;
        this.props.navigation.goBack();
      };

    componentDidMount() {
        // BackHandler.addEventListener('hardwareBackPress', this.onBackButtonPressAndroid);
        const { params } = this.props.navigation.state;
        const title = params ? params.title : null;
        this.setState({
           title:title
        });
    }

    render() {

        const {title} = this.state;
        return (
            <View style={{flex: 1, backgroundColor: '#F8F8F8'}}>
                <View style={styles.headerContainer}>
                    <TouchableOpacity activeOpacity={.8} style={{flexDirection: 'row', alignItems: 'center', flex: 1}} onPress={()=>{ this.props.navigation.goBack() }}>
                        <Image source={require('../../img/i_goback.png')} resizeMode={'contain'} style={styles.headerLeftImg}/>
                    </TouchableOpacity>
                    <TouchableOpacity activeOpacity={1} style={{flex: 1}} onPress={()=>{alert('24h')}}>
                    </TouchableOpacity>
                </View>
                <ScrollView>
                    <View style={{padding: 10}}>
                        <Text style={{fontSize: 22, fontWeight: 'bold', color: '#2c2c2c', marginBottom: 10, lineHeight: 35}}>{title}</Text>
                    </View>
                    <Text style={{ color: '#2c2c2c', lineHeight: 30, fontSize:16}}>
                        '（新闻联播）：中共中央总书记、国家主席、中央军委主席、中央国家安全委员会主席习近平4月17日下午主持召开十九届中央国家安全委员会第一次会议并发表重要讲话。习近平强调，要加强党对国家安全工作的集中统一领导，正确把握当前国家安全形势，全面贯彻落实总体国家安全观，努力开创新时代国家安全工作新局面，为实现“两个一百年”奋斗目标、实现中华民族伟大复兴的中国梦提供牢靠安全保障。'
                    </Text>
                </ScrollView>

            </View>

        );
    }

}

const htmlStyles = StyleSheet.create({

    p: {
        color: '#2c2c2c',
        lineHeight: 30,
        fontSize:16
    }

});
const styles = StyleSheet.create({
    headerContainer: {
        flexDirection: 'row',
        backgroundColor: '#d81e06',
        justifyContent: 'space-around',
        alignItems: 'center',
        height: 70,
        paddingTop: 0
    },
    headerLeftImg: {
        width: 25,
        height: 25,
    },
    headerCenterContainer: {
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        height: 36,
        flex: 4,
    },
    headerCenterText: {
        fontSize: 18,
        color: '#F8F8F8'
    },
    headerRightImg: {
        width: 40,
        height: 40,
    },

});

