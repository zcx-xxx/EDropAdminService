import xlrd
import xlwt
import requests
import json
import time

def geocodeB(address):
    """
    @ address: 名称字符串
    @ 返回值：经度，纬度
    """
    base_url = "http://api.map.baidu.com/geocoder?address={address}&output=json&key=DDLwA2CBFGHRpxFzFx3K5KnBQtHP4hte".format(address=address)
    # print(address)
    response = requests.get(base_url)
    # print(response.status_code)
    # print(len(response.content))
    if (response.status_code == 200) and (len(response.content) < 300):
        answer = response.json()
        latitude = answer['result']['location']['lng']
        longitude = answer['result']['location']['lat']
        return latitude, longitude
    else:
        return geocodeB(address)

if __name__ == "__main__":
    # 打开文件
    excel_file = xlrd.open_workbook('C:/Users/13071/Desktop/石家庄市小区.xlsx')
    # 根据索引打开指定的工作表
    read_sheet = excel_file.sheet_by_index(0)
    # 获取行数
    rows = read_sheet.nrows

    # 创建一个新的工作表
    write_book = xlwt.Workbook(encoding='utf-8')
    write_sheet = write_book.add_sheet("sheet1")

    for i in range(0, rows):
        address = read_sheet.cell(i, 2).value + " " + read_sheet.cell(i, 3).value + " " + read_sheet.cell(i, 4).value
        latitude_, longitude_ = geocodeB(address)
        data = (latitude_, longitude_, read_sheet.cell(i, 2).value, read_sheet.cell(i, 3).value, read_sheet.cell(i, 4).value)
        for j in range(len(data)):
            write_sheet.write(i, j, data[j])
    
    write_book.save('C:/Users/13071/Desktop/小区坐标.xls')
