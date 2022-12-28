import React, { Component } from "react";
//import CommonApiService from "../../module/common/CommonApiService"
import NaverApiService from "../../../api/openApi/NaverApiService"

import Table from '@material-ui/core/Table'
import TableBody from '@material-ui/core/TableBody'
import TableCell from '@material-ui/core/TableCell'
import TableHead from '@material-ui/core/TableHead'
import TableRow from '@material-ui/core/TableRow'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'
import CreateIcon from '@material-ui/icons/Create'
import DeleteIcon from '@material-ui/icons/Delete'
import { TextField } from "@material-ui/core";
import KakaoApiService from "../../../api/openApi/KakaoApiService";

class NaverApiListComponent extends Component{

    constructor(props) {
        super(props);

        this.state = {
            queryText: null,
            naverNewsList: [],
            message: null
        }
    }

    onChange = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        })
    }
/****
    componentDidMount() {
        this.reloadUserList();
    }
*/
    reloadNaverNewsList = () => {
        //ApiService.fetchUsers()
        // let query = {
        //     queryText: this.state.queryText,
        // }

        NaverApiService.getNaverNews(this.state.queryText)
        .then( res => {
            this.setState({
                naverNewsList: res.data
            })
        })
        .catch(err => {
            console.log('reload Error!', err);
        })
    }

    getNaverNewsList = () => {
        NaverApiService.getNaverNews(this.state.queryText)
        .then( res => {
            this.setState({
                naverNewsList: res.data
            })
        })
        .catch(err => {
            console.log('reload Error!', err);
        })
    }
 
    kakaoLogin = () => {
        KakaoApiService.getKakaoAccessKey()
        .then( res => {
            window.open(res.data, '_self')
        })
        .catch(err => {
            console.log('Kakao Api Err ! ', err)
        })
    }


/**
    deleteUser = (userId) => {
        ApiService.deleteUser(userId)
        .then(res => {
            this.setState({
                message: 'User Deleted !'
            });
            this.setState({
                users: this.state.users.filter( user =>
                    user.id !== userId)
            });
        })
        .catch(err => {
            console.log('Delete Error!')
        })
    }

    editUser = (id) => {
        window.localStorage.setItem("userId", id);
        this.props.history.push('/edit-user');
    }

    addUser = () => {
        window.localStorage.removeItem("userId");
        this.props.history.push('/add-user');
    }
 */
    render() {

        return(
            <div style={style}>
                <form style={formContainer}>
                    <TextField type="text" placeholder="검색어 입력" name="queryText" value={this.state.queryText} onChange={this.onChange} />
                     
                    <Button variant="contained" color="primary" onClick={this.getNaverNewsList}>Get News</Button>

                    <Button variant="contained" color="primary" onClick={this.kakaoLogin}>Kakao Login</Button>
                </form>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>제목</TableCell>
                            <TableCell>링크</TableCell>
                            <TableCell>기사</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.naverNewsList.map(naverNewsList=>
                            <TableRow key={naverNewsList.title}>
                                <TableCell component="th" scope="title">{naverNewsList.title}</TableCell>
                                <TableCell component="a" scope="link" href={naverNewsList.link} target="_blank">링크</TableCell>
                                <TableCell component="th" scope="description">{naverNewsList.description}</TableCell>
                            </TableRow>    
                        )}
                    </TableBody>
                </Table>

            </div>
        );
    }

}

const formContainer = {
    display: 'flex',
    flexFlow: 'row wrap'
}

const style = {
     justifyContents: 'center'
    , height: '900px'
}

export default NaverApiListComponent;