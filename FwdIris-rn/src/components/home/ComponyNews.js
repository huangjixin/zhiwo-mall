import React from "react";
import {Image, Text, Button,View,FlatList} from "react-native";
import  {ComponyNewsItem} from "./ComponyNewsItem";
import  {Router} from "../../common/Router";

export  class  ComponyNews extends React.Component{

    constructor () {
        super();
        //1.初始化state
        this.state = {
            type: '1',
            refreshing: false,
            data: [
                {id:1,title: '富衛集團再次全力支持Clockenflap盛事擔任十周年的官方夥伴'},
                {id:2,title: '富衛集團完成收購日本 AIG 富士生命保險公司'},
                {id:3,title: '富衛香港推出全新生活體驗平台 FWD MAX'},
                {id:4,title: '富衛香港全年業績刷新紀錄'},
                {id:5,title: '富衛集團再次全力支持Clockenflap盛事擔任十周年的官方夥伴'},
                {id:6,title: '富衛集團完成收購日本 AIG 富士生命保險公司'},
                {id:7,title: '富衛香港推出全新生活體驗平台 FWD MAX'},
                {id:8,title: '富衛香港全年業績刷新紀錄'},
                {id:9,title: '富衛香港推出全新生活體驗平台 FWD MAX'},
                {id:10,title: '富衛香港全年業績刷新紀錄'},
            ]
        }
    }

    //2.发请求
    async componentDidMount() {
        console.log('***************Request Start********************');
        // console.log('Waiting response.. .. ..');
        //
        // await fetch('http://jsonplaceholder.typicode.com/albums')
        //     .then(response => response.json())
        //     .then(json => {
        //         const data = [];
        //         for(var i=0;i<4;i++){
        //             data.push(json[i]);
        //         }
        //
        //         //4.构建&更新state
        //         this.setState({
        //             data: data
        //         });
        //     })
        //     .catch((error) => {
        //         console.error(error);
        //     });

        console.log('***************Request End********************');
    }

    toDetail = item => {
        Router.toScreen('NewsDetailScreen',{
            title:item.title
        });
    };

    _renderItem = ({item}) => {
        return(
            <ComponyNewsItem title={item.title} onPress={() => this.toDetail(item)}/>
        )
    }
    _renderFooter = () => {
        let len = this.state.data.length;
        return (
            <View style={{flexDirection: 'row', justifyContent:'center', alignItems: 'center', height: len<1?0:40}}>
                <Image source={require('../../../img/i_loading.gif')} resizeMode={'contain'} style={{width: 20, height: 20, marginRight: 5 }} />
                <Text>正在加载...</Text>
            </View>
        )
    };

    _onEndReached = () => {
        console.log("_onEndReached......");
    };

    _renderRefresh = () => {
        console.log("_renderRefresh......");
        this.setState({refreshing: true});
    };
    render() {
        const {type,data} = this.state;
        return (
            <View>
                {/*<View style={{backgroundColor:'#F2F2F2',height:5}}/>*/}
                {/*{*/}
                    {/*data.map((v, i) => <ComponyNewsItem title={v.title} onPress={() => this.toDetail(v)} />)*/}
                {/*}*/}
                {/*<View style={{height:10}}/>*/}

                <FlatList
                    data={data}
                    initialNumToRender={10}
                    onEndReachedThreshold={0.1}
                    onEndReached={ this._onEndReached }
                    renderItem={this._renderItem}
                    ListFooterComponent={ this._renderFooter }
                    refreshing={ this.state.refreshing }
                    onRefresh={ this._renderRefresh }
                />
            </View>
        );
    }
}