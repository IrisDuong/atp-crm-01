import React,{useEffect, useState} from 'react'
import { Navigate } from 'react-router-dom';
import Home from "./Home";
import { getStorage, getAuthentication} from '../../service/auth/auth.service';
import {STORAGE_KEYS} from '../../util/app.config';
function Main() {
  const [isLoggedIn, setIsLoggedIn] = useState(null);
  const checkAuthen = async () => {
    // var authenInfo = await getAuthentication();
    var authenInfo = {};
    if(authenInfo != null){
      setIsLoggedIn(true);
    }else{
      setIsLoggedIn(false)
    }
  }
  useEffect(()=>{
    checkAuthen()
  },[])
  if(isLoggedIn === null){
    return <div>Loading...</div>
  }
  return isLoggedIn ?  <Home></Home> : <Navigate to="/authen" replace="true"></Navigate>;
}

export default Main;