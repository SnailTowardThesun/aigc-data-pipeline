// Author: hankun1991@outlook.com

import React, {useEffect, useState} from 'react';
import {Layout, Menu} from '@arco-design/web-react';
import "@arco-design/web-react/dist/css/arco.css";
import './App.css';

import {FrontendFooter} from "./components/footer";
import {FrontendHeader} from "./components/header";
import {DataSetDetails} from "./components/DataSetDetails";
import {getDataSetList, CommonResponse, GetDataSetsData} from "./clients/http-clients";
const Sider = Layout.Sider;
const Header = Layout.Header;
const Content = Layout.Content;
const Footer = Layout.Footer;

function App() {
    const [curDataSetIdx, setCurDataSetIdx] = useState<number>(0);
    const [DataSets, setDataSets] = useState<CommonResponse<GetDataSetsData>>();

    useEffect(() => {
        const fetchCols = async () => {
            const ret = await getDataSetList(0, 10);
            if (ret === null) {
                return;
            }

            console.log(ret.data)
            if (ret.data.Code !== 0) {
                console.error(ret.data.Message);
                return;
            }


            setDataSets(ret.data);
            setCurDataSetIdx(0);
        }

        if (!DataSets) {
            fetchCols().catch(console.error);
        }
    }, [curDataSetIdx]);

    const genMenuItems = () => {
        if (DataSets === undefined) return (<></>);
        return (
            <Menu
                style={{ width: 200, borderRadius: 4 }}
            >
                {DataSets.Data.Datasets.map((item) => {
                    return <Menu.Item key={item.Id}>{item.Name}</Menu.Item>
                })}
            </Menu>
        )
    }

    return (
        <div className="layout-basic-demo">
            <Layout>
                <Header>
                    <FrontendHeader />
                </Header>
                <Layout>
                    <Sider>
                        {genMenuItems()}
                    </Sider>
                    <Content>
                        <DataSetDetails />
                    </Content>
                </Layout>
                <Footer>
                    <FrontendFooter />
                </Footer>
            </Layout>
        </div>
    );
}

export default App;
