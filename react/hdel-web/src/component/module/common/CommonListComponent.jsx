import React, { Component } from "react";
//import CommonApiService from "../../module/common/CommonApiService"
import CommonApiService from "../../../api/common/CommonApiService"

import Table from '@material-ui/core/Table'
import TableBody from '@material-ui/core/TableBody'
import TableCell from '@material-ui/core/TableCell'
import TableHead from '@material-ui/core/TableHead'
import TableRow from '@material-ui/core/TableRow'
import Button from '@material-ui/core/Button'
import Typography from '@material-ui/core/Typography'
import CreateIcon from '@material-ui/icons/Create'
import DeleteIcon from '@material-ui/icons/Delete'

class CommonListComponent extends Component{

    constructor(props) {
        super(props);

        this.state = {
            users: [],
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
        CommonApiService.getUsers()
        .then( res => {
            this.setState({
                users: res.data
            })
        })
        .catch(err => {
            console.log('reload Error!', err);
        })
    }

    getUserList = () => {
        //ApiService.fetchUsers()
        CommonApiService.getUsers()
        .then( res => {
            this.setState({
                users: res.data
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
            <div>
                <Typography variant="h4" style={style}>User List</Typography>
                <Button variant="contanined" color="primary" onClick={this.addUser}>Add User</Button>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>FirstName</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.users.map(user=>
                            <TableRow key={user.id}>
                                <TableCell component="th" scope="user">{user.id}</TableCell>
                                <TableCell>{user.firstName}</TableCell>
                            </TableRow>    
                        )}
                    </TableBody>
                </Table>

            </div>
        );
    }

}

const style = {
    display: 'flex',
    justifyContents: 'center'
}

export default CommonListComponent;