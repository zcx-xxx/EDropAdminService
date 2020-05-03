import random
import math
import xlrd
import xlwt
import json

#  参数含义 
# base_log：经度基准点， 
# base_lat：维度基准点， 
# radius：距离基准点的半径
def generate_random_gps(base_log=None, base_lat=None, radius=None):
    radius_in_degrees = radius / 111300
    u = float(random.uniform(0.0, 1.0))
    v = float(random.uniform(0.0, 1.0))
    w = radius_in_degrees * math.sqrt(u)
    t = 2 * math.pi * v
    x = w * math.cos(t)
    y = w * math.sin(t)
    longitude = y + base_log
    latitude = x + base_lat
    # 这里是想保留14位小数
    loga = '%.14f' % longitude
    lata = '%.14f' % latitude
    return loga, lata

# 114.529344,38.003631 软件学院坐标
if __name__ == "__main__":
    # 打开文件
    excel_file = xlrd.open_workbook('C:/Users/13071/Desktop/小区坐标.xls')
    # 根据索引打开指定的工作表
    read_sheet = excel_file.sheet_by_index(0)
    # 获取行数
    rows = read_sheet.nrows
    # 创建一个新的工作表
    # write_book = xlwt.Workbook(encoding='utf-8')
    # write_sheet = write_book.add_sheet("sheet2")

    tot = 0
    list = []
    for i in range(rows):
        address = {}
        for k in range(0, 5):
            address[k] = read_sheet.cell(i, k).value
        longitude = address[0]
        latitude = address[1]

        if (address[3] == '裕华区'):
            for j in range(80):
                address[0], address[1] = generate_random_gps(base_log=longitude, base_lat=latitude, radius=100)
                address[5] = random.randint(300, 400)
                # for p in range(len(address)):
                #     write_sheet.write(tot, p, address[p])
                # write_sheet.write(tot, 5, random.randint)
                list.append({'coord':[address[0], address[1]], 'elevation':address[5]})
                tot += 1
        elif (address[3] == '长安区'):
            for j in range(40):
                # print("haha")
                address[0], address[1] = generate_random_gps(base_log=longitude, base_lat=latitude, radius=100)
                address[5] = random.randint(1, 50)
                # for p in range(len(address)):
                #     write_sheet.write(tot, p, address[p])
                # write_sheet.write(tot, 5, random.randint)
                list.append({'coord':[address[0], address[1]], 'elevation':address[5]})
                tot += 1
        else:
            for j in range(20):
                # print("haha")
                address[0], address[1] = generate_random_gps(base_log=longitude, base_lat=latitude, radius=100)
                address[5] = random.randint(1, 50)
                # for p in range(len(address)):
                #     write_sheet.write(tot, p, address[p])
                # write_sheet.write(tot, 5, random.randint)
                list.append({'coord':[address[0], address[1]], 'elevation':address[5]})
                tot += 1

    with open('C:/Users/13071/Desktop/data.json', 'w') as f:
        json.dump(list, f)
    # write_book.save('C:/Users/13071/Desktop/random_address.xls')