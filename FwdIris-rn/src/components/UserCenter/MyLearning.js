import React from 'react';
import {StyleSheet, ScrollView, Text ,View,Image,Button,ImageBackground,TouchableWithoutFeedback,FlatList} from 'react-native';
import { AnimatedCircularProgress } from 'react-native-circular-progress';
export class MyLearning extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            dataSource:[]
        }
        this.fetchData = this.fetchData.bind(this);
    }
    componentWillMount() {
        this.fetchData();
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
    render() {
        return (
            <ScrollView style={{marginBottom:10,}}>
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
                {/*
                <View>
                    <View style={{backgroundColor:'white',flexDirection: 'row',borderRadius:5,padding:10,marginTop:10,
                        borderColor:'#CCCCCC',borderWidth:1,border:'sold',marginLeft:1,marginRight:1,}}>
                        <View style={{flex:1.3,justifyContent: 'center'}}>
                            <View style={{borderColor:'#FFBC59',borderWidth:2,border:'sold',borderRadius:20,width:40,height:40}}>
                                <Text style={{flex:1,fontSize:20,textAlign:'center',paddingTop:5}}>岗</Text>
                            </View>
                        </View>
                        <View style={{flex:7.2}}>
                            <Text style={{fontSize:15,fontWeight:'bold',}}>初级营销员</Text>
                            <View style={{flexDirection: 'row',marginTop:10,}}>
                                <Text style={{color:'#B6B7B5'}}>学后奖励:</Text>
                                <Image style={styles.learningImage} source={require('../../../img/UserCenter/oracleImage.png')}/>
                                <Image style={styles.learningImage} source={require('../../../img/UserCenter/honorImage.png')}/>
                                <Image style={styles.learningImage} source={require('../../../img/UserCenter/tickImage.png')}/>
                                <Text style={{marginLeft:10,color:'#FFBC59'}}>12.01~12.11</Text>
                            </View>

                        </View>
                        <View style={{flex:1.5,justifyContent: 'center'}}>
                            <CircularProgress itemValue={'75%'}/>
                        </View>
                    </View>
                </View>*/}




                <View>
                    <FlatList
                        data={this.state.dataSource}
                        renderItem={({item}) =>
                            <View style={{backgroundColor:'white',flexDirection: 'row',borderRadius:5,padding:10,marginTop:10,
                                borderColor:'black',borderWidth:0.5,border:'sold',marginLeft:1,marginRight:1,}}>
                                <View style={{flex:1.8,}}>
                                    <View style={{borderColor:'#FFBC59',borderWidth:2,border:'sold',borderRadius:20,width:40,height:40,marginLeft:10,}}>
                                        <Text style={{fontSize:20,marginLeft:8,marginTop:5,}}>岗</Text>
                                    </View>
                                </View>
                                <View style={{flex:8.2,}}>
                                    <View style={{flexDirection: 'row',paddingBottom:10,}}>
                                        <View style={{flex:8}}>
                                            <Text style={{fontSize:15,fontWeight:'bold',}}>初级营销员</Text>
                                            <View style={{flexDirection: 'row',marginTop:10,}}>
                                                <Text style={{color:'#B6B7B5'}}>学后奖励:</Text>
                                                <Image style={styles.learningImage} source={require('../../../img/UserCenter/oracleImage.png')}/>
                                                <Image style={styles.learningImage} source={require('../../../img/UserCenter/honorImage.png')}/>
                                                <Image style={styles.learningImage} source={require('../../../img/UserCenter/tickImage.png')}/>
                                                <Text style={{marginLeft:10,color:'#FFBC59'}}>12.01~12.11</Text>
                                            </View>
                                        </View>
                                        <View style={{flex:2}}>
                                            <CircularProgress itemValue={'75%'}/>
                                        </View>
                                    </View>
                                </View>
                            </View>
                        }
                    />
                </View>


                <View>
                    <View style={{backgroundColor:'white',flexDirection: 'row',borderRadius:5,padding:10,marginTop:10,
                        borderColor:'black',borderWidth:0.5,border:'sold',marginLeft:1,marginRight:1,}}>
                        <View style={{flex:1.8,}}>
                            <View style={{borderColor:'#FFBC59',borderWidth:2,border:'sold',borderRadius:20,width:40,height:40,marginLeft:10,}}>
                                <Text style={{fontSize:20,marginLeft:8,marginTop:5,}}>必</Text>
                            </View>
                        </View>
                        <View style={{flex:8.2,}}>
                            <View style={{flexDirection: 'row',borderBottomWidth:1,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',paddingBottom:10,}}>
                                <View style={{flex:8}}>
                                    <Text>资格证书名称</Text>
                                    <View style={{flexDirection: 'row',marginTop:10,}}>
                                        <Text style={{color:'#B6B7B5'}}>学后奖励:</Text>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/oracleImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/honorImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/tickImage.png')}/>
                                        <Text style={{marginLeft:10,color:'#FFBC59'}}>12.01~12.11</Text>
                                    </View>
                                </View>
                                <View style={{flex:2}}>
                                    <CircularProgress itemValue={'75%'}/>
                                </View>
                            </View>

                            <View style={{flexDirection: 'row',marginTop:10,}}>
                                <View style={{flex:8}}>
                                    <Text>资格证书名称</Text>
                                    <View style={{flexDirection: 'row',marginTop:10,}}>
                                        <Text style={{color:'#B6B7B5'}}>学后奖励:</Text>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/oracleImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/honorImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/tickImage.png')}/>
                                        <Text style={{marginLeft:10,color:'#FFBC59'}}>长期有效</Text>
                                    </View>
                                </View>
                                <View style={{flex:2}}>
                                    <CircularProgress itemValue={'75%'}/>
                                </View>
                            </View>
                        </View>
                    </View>
                </View>




                <View>
                    <View style={{backgroundColor:'white',flexDirection: 'row',borderRadius:5,padding:10,marginTop:10,
                        borderColor:'black',borderWidth:0.5,border:'sold',marginLeft:1,marginRight:1,}}>
                        <View style={{flex:1.8,}}>
                            <View style={{borderColor:'#FFBC59',borderWidth:2,border:'sold',borderRadius:20,width:40,height:40,marginLeft:10,}}>
                                <Text style={{fontSize:20,marginLeft:8,marginTop:5,}}>班</Text>
                            </View>
                        </View>
                        <View style={{flex:8.2,}}>
                            <View style={{flexDirection: 'row',borderBottomWidth:1,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',paddingBottom:10,}}>
                                <View style={{flex:8}}>
                                    <Text>新人培训班17级</Text>
                                    <View style={{flexDirection: 'row',marginTop:10,}}>
                                        <Text style={{color:'#B6B7B5'}}>学后奖励:</Text>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/oracleImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/honorImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/tickImage.png')}/>
                                        {/*<Text style={{marginLeft:10,color:'#FFBC59'}}>12.01~12.11</Text>*/}
                                    </View>
                                </View>
                                <View style={{flex:2}}>
                                    <CircularProgress itemValue={'75%'}/>
                                </View>
                            </View>
                            <View style={{flexDirection: 'row',borderBottomWidth:1,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',paddingBottom:10,paddingTop:10,}}>
                                <View style={{flex:8}}>
                                    <Text>保险销售技能培训班</Text>
                                    <View style={{flexDirection: 'row',marginTop:10,}}>
                                        <Text style={{color:'#B6B7B5'}}>学后奖励:</Text>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/oracleImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/honorImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/tickImage.png')}/>
                                        {/*<Text style={{marginLeft:10,color:'#FFBC59'}}>12.01~12.11</Text>*/}
                                    </View>
                                </View>
                                <View style={{flex:2}}>
                                    <CircularProgress itemValue={'75%'}/>
                                </View>
                            </View>
                            <View style={{flexDirection: 'row',marginTop:10,}}>
                                <View style={{flex:8}}>
                                    <Text>新产品销售推广培训班</Text>
                                    <View style={{flexDirection: 'row',marginTop:10,}}>
                                        <Text style={{color:'#B6B7B5'}}>学后奖励:</Text>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/oracleImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/honorImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/tickImage.png')}/>
                                        {/*<Text style={{marginLeft:10,color:'#FFBC59'}}>长期有效</Text>*/}
                                    </View>
                                </View>
                                <View style={{flex:2}}>
                                    <CircularProgress itemValue={'75%'}/>
                                </View>
                            </View>
                        </View>
                    </View>
                </View>





                {/*

                <View>
                    <View style={{backgroundColor:'white',flexDirection: 'row',borderRadius:5,padding:10,marginTop:10,
                        borderColor:'black',borderWidth:0.5,border:'sold',marginLeft:1,marginRight:1,}}>
                        <View style={{flex:1.5,}}>
                            <View style={{borderColor:'#FFBC59',borderWidth:2,border:'sold',borderRadius:20,width:40,height:40,marginLeft:10,}}>
                                <Text style={{fontSize:20,marginLeft:8,marginTop:5,}}>班</Text>
                            </View>
                        </View>
                        <View style={{flex:8.5,}}>
                            <View style={{flexDirection: 'row',borderBottomWidth:1,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',paddingBottom:10,marginBottom:10,}}>
                                <View style={{flex:8}}>
                                    <Text>新人培训版17级</Text>
                                    <View style={{flexDirection: 'row',marginTop:10,}}>
                                        <Text style={{color:'#B6B7B5'}}>学后奖励:</Text>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/oracleImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/honorImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/tickImage.png')}/>
                                        <Text style={{marginLeft:10,color:'#FFBC59'}}>12.01~12.11</Text>
                                    </View>
                                </View>
                                <View style={{flex:2}}>
                                    <CircularProgress itemValue={'75%'}/>
                                </View>
                            </View>


                            <View style={{flexDirection: 'row',borderBottomWidth:1,borderBottomStyle:'sold',borderBottomColor:'#CCCCCC',paddingBottom:10,marginBottom:10}}>
                                <View style={{flex:8}}>
                                    <Text>保险销售技能培训班</Text>
                                    <View style={{flexDirection: 'row',marginTop:10,}}>
                                        <Text style={{color:'#B6B7B5'}}>学后奖励:</Text>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/oracleImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/honorImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/tickImage.png')}/>
                                        <Text style={{marginLeft:10,color:'#FFBC59'}}>12.01~12.11</Text>
                                    </View>
                                </View>
                                <View style={{flex:2}}>
                                    <CircularProgress itemValue={'75%'}/>
                                </View>
                            </View>


                            <View style={{flexDirection: 'row',}}>
                                <View style={{flex:8}}>
                                    <Text>新产品销售推广培训班</Text>
                                    <View style={{flexDirection: 'row',marginTop:10,}}>
                                        <Text style={{color:'#B6B7B5'}}>学后奖励:</Text>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/oracleImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/honorImage.png')}/>
                                        <Image style={styles.learningImage} source={require('../../../img/UserCenter/tickImage.png')}/>
                                        <Text style={{marginLeft:10,color:'#FFBC59'}}>长期有效</Text>
                                    </View>
                                </View>
                                <View style={{flex:2}}>
                                    <CircularProgress itemValue={'75%'}/>
                                </View>
                            </View>
                        </View>
                    </View>
                </View>*/}

            </ScrollView>
        );
    }
}
class CircularProgress extends React.Component{
    render() {
        const {
            itemValue
        } = this.props;

        return (

            <View style={styles.container}>
                <View style={{height:50}}>
                    <AnimatedCircularProgress
                        size={50}
                        width={2}
                        fill={75}
                        rotation={0}
                        tintColor="#4FBDD5"
                        backgroundColor="#DCDCDC">
                        {
                            (fill) => (
                                <View >
                                    <Text style = {{fontSize:18,color:'#FFB207'}}>{itemValue}</Text>
                                </View>
                            )
                        }
                    </AnimatedCircularProgress>
                </View>
            </View>
        );
    }
}
const styles = StyleSheet.create({
    container: {flex: 1,},
    learningImage:{width:20,height:20,marginLeft:10,},
});