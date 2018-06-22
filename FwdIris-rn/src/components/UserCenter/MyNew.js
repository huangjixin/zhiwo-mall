import React from 'react';
import {StyleSheet, ScrollView, Text ,View,Image,Button,ImageBackground,TouchableWithoutFeedback
,FlatList} from 'react-native';
export class MyNew extends React.Component {
    constructor(props){
        super(props);
        this.state  = {
            dataSource:[]
        };

        this.fetchData = this.fetchData.bind(this);
    }
    
    // 网络请求
    fetchData() {
        fetch('https://facebook.github.io/react-native/movies.json', {
            })
            .then((response) => response.json())
            .then((responseData) => {
                let data = this.state.dataSource.concat(responseData.movies);
                
                this.setState( {
                    dataSource:data
                })
            }).done();
    }
    
    componentWillMount() {
        this.fetchData();
    }

    render() {
        return (
            <ScrollView style={{backgroundColor:'#F2F2F2',marginBottom:10,}}>
                <View style={{backgroundColor:'#D1D1D1',}}>
                    <View style={{justifyContent:'center',alignItems: 'center',flex:1,}}>
                        <Image style={{width: 100, height: 100,borderRadius:50,marginTop:20,}} source={require('../../../img/UserCenter/UserImage.jpeg')} />
                    </View>
                    <View style={{flexDirection: 'row',marginTop:10,}}>
                        <View style={{flex:1,}}>
                            <TouchableWithoutFeedback  onPress={() => this.props.navigation.goBack()}>
                                <Image style={{width:30,height:30,marginTop:5,marginLeft:15,marginBottom:10,}}source={require('../../../img/UserCenter/GoBack.png')}/>
                            </TouchableWithoutFeedback>
                        </View>
                        <Text style={{flex:1,marginLeft:-50,color:'white',fontWeight:'bold',fontSize: 15,marginTop:5,}}>七月流火</Text>
                    </View>
                </View>


                    <FlatList
                            data={this.state.dataSource}
                            renderItem={({item}) =>
                                <View style={{flex:1,marginTop:10,justifyContent:'center'}}>
                                    <View style={{justifyContent:'center',alignItems: 'center',flex:1,height:40,}}><Text>2018-01-11 15:00</Text></View>
                                    <View style={{backgroundColor:'white',borderRadius:5,
                                        borderColor:'#CCCCCC',borderWidth:0.5,border:'sold',marginLeft:1,marginRight:1,paddingBottom:10,paddingTop:10,}}>
                                        <View style={{borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:10,marginRight:10,}}>
                                            <Text style={{marginBottom:10,}}>照会消息</Text>
                                        </View>
                                        <Text style={styles.detailText}>会议时间：2018-01-12 09:00  ~  2018-01-12 11:00</Text>
                                        <Text style={styles.detailText}>会议地点：{item.title}</Text>
                                        <Text style={styles.detailText}>会议内容：本季度销售总结&下一季度销售目标确认事宜。</Text>
                                    </View>
                                </View>
                            }
                />
                


                {/* <View>
                    <View style={{justifyContent:'center',alignItems: 'center',flex:1,height:40,}}><Text>2018-01-11 15:00</Text></View>
                    <View style={{backgroundColor:'white',borderRadius:5,
                        borderColor:'#CCCCCC',borderWidth:0.5,border:'sold',marginLeft:1,marginRight:1,paddingBottom:10,paddingTop:10,}}>
                        <View style={{borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:10,marginRight:10,}}>
                            <Text style={{marginBottom:10,}}>照会消息</Text>
                        </View>
                        <Text style={styles.detailText}>会议时间：2018-01-12 09:00  ~  2018-01-12 11:00</Text>
                        <Text style={styles.detailText}>会议地点：裸心社 Tiger 5F</Text>
                        <Text style={styles.detailText}>会议内容：本季度销售总结&下一季度销售目标确认事宜。</Text>
                    </View>
                </View>


                <View>
                    <View style={{justifyContent:'center',alignItems: 'center',flex:1,height:40,}}><Text>2018-01-11 15:00</Text></View>
                    <View style={{backgroundColor:'white',borderRadius:5,
                        borderColor:'#CCCCCC',borderWidth:0.5,border:'sold',marginLeft:1,marginRight:1,paddingBottom:10,paddingTop:10,}}>
                        <View style={{borderBottomWidth:0.5,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',marginLeft:10,marginRight:10,}}>
                            <Text style={{marginBottom:10,}}>重要事项</Text>
                        </View>
                        <View style={[styles.detailText,{flexDirection: 'row',}]}>
                            <Text>升级版本：</Text>
                            <Text>V1.0.18.001</Text>
                        </View>
                        <View style={[styles.detailText,{flexDirection: 'row',}]}>
                            <Text>升级版本：</Text>
                            <View>
                                <Text>1.优化APP启动后，欢迎画面展示效果。</Text>
                                <Text>2.优化菜单。</Text>
                            </View>
                        </View>
                    </View>
                </View> */}








            </ScrollView>


        );
    }
}
const styles = StyleSheet.create({
    container:{flex: 1,},
    detailText:{marginLeft:20,marginTop:10,},
});