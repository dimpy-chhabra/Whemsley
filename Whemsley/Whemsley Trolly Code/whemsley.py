import RPi.GPIO as GPIO
import urllib2, urllib
import time


def createBoolList(size=8):
    ret = []
    for i in range(8):
        ret.append(False)
    return ret


class HX711:
    def __init__(self, dout, pd_sck, gain=128):
        self.PD_SCK = pd_sck
        self.DOUT = dout

        GPIO.setup(self.PD_SCK, GPIO.OUT)
        GPIO.setup(self.DOUT, GPIO.IN)

        self.GAIN = 0
        self.OFFSET = 0
        self.SCALE = 1
        self.lastVal = 0

        #GPIO.output(self.PD_SCK, True)
        #GPIO.output(self.PD_SCK, False)

        self.set_gain(gain);

    def is_ready(self):
        return GPIO.input(self.DOUT) == 0

    def set_gain(self, gain):
        if gain is 128:
            self.GAIN = 1
        elif gain is 64:
            self.GAIN = 3
        elif gain is 32:
            self.GAIN = 2

        GPIO.output(self.PD_SCK, False)
        self.read()

    def read(self):
        while not self.is_ready():
            #print("WAITING")
            pass

        dataBits = [createBoolList(), createBoolList(), createBoolList()]

        for j in range(2, -1, -1):
            for i in range(7, -1, -1):
                GPIO.output(self.PD_SCK, True)
                dataBits[j][i] = GPIO.input(self.DOUT)
                GPIO.output(self.PD_SCK, False)

        #set channel and gain factor for next reading
        #for i in range(self.GAIN):
        GPIO.output(self.PD_SCK, True)
        GPIO.output(self.PD_SCK, False)

        #check for all 1
        if all(item == True for item in dataBits[0]):
            return self.lastVal

        bits = []
        for i in range(2, -1, -1):
            bits += dataBits[i]

        self.lastVal = int(''.join(map(str, bits)), 2)
        return self.lastVal

        '''
        data = [0,0,0]
        for i in range(0,3):
            #print(''.join(map(str, dataBits[i])))
            data[i] = int(''.join(map(str, dataBits[i])), 2)
            #print(data[i])
        #data[2] ^= 0x80
        return data[2] << 16 | data[1] << 8 | data[0]
        '''

    def read_average(self, times=3):
        sum = 0
        for i in range(times):
            sum += self.read()

        return sum / times

    def get_value(self, times=3):
        return self.read_average(times) - self.OFFSET

    def get_units(self, times=3):
        return self.get_value(times) / self.SCALE

    def tare(self, times=15):
        sum = self.read_average(times)
        self.set_offset(sum)

    def set_scale(self, scale):
        self.SCALE = scale

    def set_offset(self, offset):
        self.OFFSET = offset

    def power_down(self):
        GPIO.output(self.PD_SCK, False)
        GPIO.output(self.PD_SCK, True)

    def power_up(self):
        GPIO.output(self.PD_SCK, False)
        
############# EXAMPLE
hx = HX711(9, 11)
hx.set_scale(7050)
hx.tare()

lock_status = 0
capa_status = 0

while True:
    
    try:
        val = hx.get_units(3)

        # If lock_status is 1 or 2 or 3 ==> Valid
        #   1 >> Unlock Mode
        #   2 >> Lock Mode
        #   3 >> Security Mode
        #
        # If capa_status is 4 or 5 or 6 ==> Valid
        #   1 >> Empty
        #   2 >> Not Empty
        #   3 >> Full Capacity

        

        mydata=[('trolly_id', '01UT01010104')]
        mydata=urllib.urlencode(mydata)
        path='xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'    #the url you want to POST to
        req = urllib2.Request(path, mydata)
        req.add_header("Content-type", "application/x-www-form-urlencoded")
        page=urllib2.urlopen(req).read()
        print page

        if page=="1":  #Unlock: NO MONITORING
            while page ==1:
                mydata=[('trolly_id', '01UT01010104')]
                mydata=urllib.urlencode(mydata)
                path='xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'    #the url you want to POST to
                req = urllib2.Request(path, mydata)
                req.add_header("Content-type", "application/x-www-form-urlencoded")
                page=urllib2.urlopen(req).read()
                print page

        if page=="2": 
            val = hx.get_units(3)
            
            mydata=[('trolly_id', '01UT01010104'), ('Weight' , val)]
            mydata=urllib.urlencode(mydata)
            path='xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'    #the url you want to POST to
            req = urllib2.Request(path, mydata)
            req.add_header("Content-type", "application/x-www-form-urlencoded")
            page=urllib2.urlopen(req).read()
            print page  

            val_in_lock = 0
            while page ==2:
                val_in_lock = hx.get_units(3)
                mydata=[('trolly_id', '01UT01010104')]
                mydata=urllib.urlencode(mydata)
                path='xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'    #the url you want to POST to
                req = urllib2.Request(path, mydata)
                req.add_header("Content-type", "application/x-www-form-urlencoded")
                page=urllib2.urlopen(req).read()
                print page
                if( val_in_lock !< val+2 && val_in_lock !> val-2):
                    led.blink()

        if page =="3":     
            while page == 3:
                mydata=[('trolly_id', '01UT01010104')]
                mydata=urllib.urlencode(mydata)
                path='xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'    #the url you want to POST to
                req = urllib2.Request(path, mydata)
                req.add_header("Content-type", "application/x-www-form-urlencoded")
                page=urllib2.urlopen(req).read()
                print page       


        time.sleep(.001)
    except (KeyboardInterrupt, SystemExit):
        sys.exit()