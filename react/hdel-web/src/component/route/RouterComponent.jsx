import React from 'react';
import { BrowserRouter, Route, Routes} from 'react-router-dom';
import CommonListComponent from "../module/common/CommonListComponent";
import TempHeaderHeader from "../module/common/TempHeaderComponent";
import HoInfoListComponent from "../module/common/HoInfoListComponent";
import NaverApiListComponent from '../module/openApi/NaverApiListComponent';

const AppRouter = () => {
    return(
        <div>
            {/* 
            <BrowserRouter>
                <div style={style}>
                    12345
                    
                    <Routes>
                        <Route exact path="/" component = {CommonListComponent} />  Home 같은걸 올림
                        <Route path="/common" component = {CommonListComponent} />
                    </Routes>
                    
                    
                </div>
            </BrowserRouter>
            */}
            {/* 
            <div style={style}>
                <TempHeaderHeader />
                <HoInfoListComponent />
            </div>
            */}
            <BrowserRouter>
                <div style={style}>
                    <Routes>
                        {/* 
                        <Route exact path="/ho" component = {HoInfoListComponent} />
                        <Route path="/common" component = {CommonListComponent} />
                        */}
                        <Route exact path="/" element = {<NaverApiListComponent />} />
                        <Route path="/common" component = {CommonListComponent} />
                    </Routes>
                </div>
            </BrowserRouter>

        </div>
    );
}

const style = {
    marginTop: '20px'
}

export default AppRouter;