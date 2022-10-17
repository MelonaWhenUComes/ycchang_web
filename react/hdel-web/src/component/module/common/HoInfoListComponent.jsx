import React, { Component } from "react";
//import CommonApiService from "../../module/common/CommonApiService"
import HoInfoApiService from "../../../api/HoInfo/HoInfoApiService"

import Table from '@material-ui/core/Table'
import TableBody from '@material-ui/core/TableBody'
import TableCell from '@material-ui/core/TableCell'
import TableHead from '@material-ui/core/TableHead'
import TableRow from '@material-ui/core/TableRow'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'
import CreateIcon from '@material-ui/icons/Create'
import DeleteIcon from '@material-ui/icons/Delete'

class HoInfoListComponent extends Component{

    constructor(props) {
        super(props);

        this.state = {
            hoInfos: [],
            message: null
        }
    }

/****
    componentDidMount() {
        this.reloadUserList();
    }
*/
    reloadUserList = () => {
        //ApiService.fetchUsers()
        HoInfoApiService.getHoInfo()
        .then( res => {
            this.setState({
                hoInfos: res.data
            })
        })
        .catch(err => {
            console.log('reload Error!', err);
        })
    }

    getHoInfoList = () => {
        HoInfoApiService.getHoInfo()
        .then( res => {
            this.setState({
                hoInfos: res.data
            })
        })
        .catch(err => {
            console.log('reload Error!', err);
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
                <Typography variant="h4">Ho Information</Typography>
                
                <Button variant="contained" color="primary" onClick={this.getHoInfoList}>Get Ho Info</Button>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Project No.</TableCell>
                            <TableCell>등록일자</TableCell>
                            <TableCell>인입번호</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.hoInfos.map(hoInfo=>
                            <TableRow key={hoInfo.id}>
                                <TableCell component="th" scope="hoInfo">{hoInfo.id}</TableCell>
                                <TableCell component="th" scope="hoInfo">{hoInfo.projNo}</TableCell>
                                <TableCell component="th" scope="hoInfo">{hoInfo.regDt}</TableCell>
                                <TableCell component="th" scope="hoInfo">{hoInfo.acptCallNo}</TableCell>
                            </TableRow>    
                        )}
                    </TableBody>
                </Table>

            </div>
        );
    }

}

const style = {
    justifyContents: 'center'
    , height: '900px'
}

export default HoInfoListComponent;