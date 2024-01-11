// Author: hankun1991@outlook.com

import axios from "axios";

const host = 'http://localhost:8098'

export interface CommonResponse<T> {
    Code: number
    Message: string
    Data: T
}


export interface DataSetDetails {
    Id: string
    UserId: string
    Name: string
    Description: string
    Structure: string
    CreatedAt: string
    UpdatedAt: string
}
export async function getDataSetDetails(id: string) {
    return axios.get<DataSetDetails>(`${host}/apis/v1/dataset?id=${id}`)
}


export interface GetDataSetsData {
    Total: number
    Datasets: DataSetDetails[]

}
export async function getDataSetList(pageNumber: number, pageSize: number) {
    return axios.get<CommonResponse<GetDataSetsData>>(`${host}/apis/v1/datasets?pageNumber=${pageNumber}&pageSize=${pageSize}`)
}