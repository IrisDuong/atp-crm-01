import React,{useEffect, useState} from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { Navigate,useSearchParams} from 'react-router-dom';
import Home from "./Home";
import { getAuthentication} from '../../service/auth/auth.service';
import { setAuthenticationInfo } from '../../store/auth/authSlice';
function Main() {

  /**
   * state, const
   */
  const [searchParams,setSearchParams] = useSearchParams();
  const dispatch = useDispatch();
  const {authenticatedInfo} = useSelector(state=> state.authenStore);
  const [isAuthenticated, setIsAuthenticated] = useState(null);

  /**
   * events
   */
  const checkAuthen = async () => {
    if(authenticatedInfo){
      setIsAuthenticated(true);
    }else{
      var authenInfo = await getAuthentication();
      if(authenInfo != null){
        setIsAuthenticated(true);
        dispatch(setAuthenticationInfo(authenInfo.data));
      }else{
        console.log("[AUTHEN] : 401 -  UNAUTHORIZED")
        setIsAuthenticated(false);
      }
    }
  }

  const checkLogout = ()=>{
    console.log("[LOGOUT] :  START TO CLEAR AUTHEN INFO IN SORE AFTER LOGOUT FROM BACK-END")
    const isLogoutParams = searchParams.get("isLogout");
    if(isLogoutParams){
      console.log("[LOGOUT] :  CLEAR AUTHEN INFOR SUCCESSFULLY")
      dispatch(setAuthenticationInfo(null));
      setIsAuthenticated(false);
    }
  }
  useEffect(()=>{
    checkAuthen()
  },[])

  useEffect(()=>{
    checkLogout();
  },[searchParams])
  /**
   * render
   */
  if(isAuthenticated === null){
    return <div>Loading...</div>
  }
  return isAuthenticated ?  <Home></Home> : <Navigate to="/authen" replace="true"></Navigate>;
}

export default Main;