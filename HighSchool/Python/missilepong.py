#HOW TO PLAY THE GAME

    #PLAYER 1 USES "W" "A" "S" "D" TO MOVE
    #PLAYER 1 USES "Q" TO SHOOT THE MISSILE
    #PLAYER 2 USES ARROW KEYS TO MOVE
    #PLAYER 2 USES LSHIFT TO SHOOT THE MISSILE
    
    #POINTS ARE MADE BY SCORING ON THE OTHER SIDE (WHEN OPPONENT MISSES BALL)
    #FIRST TO 15 POINTS GETS A WIN AND GAME RESETS (WIN COUNTED)
    #FIRST TO SHOOT A MISSILE AND HIT THE WALL GETS A WIN AND GAME CONTINUES (WIN COUNTED)
    
import pygame, sys, random

#setup
pygame.init()
clock = pygame.time.Clock()

#design variables for ease
white = (255, 255, 255)
black = (0, 0, 0)
red = (255, 0, 0)
blue = (0, 0, 255)
fontBig = pygame.font.SysFont("verdana", 60, True)
fontSmall = pygame.font.SysFont("verdana", 40, True)

#sounds
pointS = pygame.mixer.Sound("pluck.wav")
lossS = pygame.mixer.Sound("peeeooop_x.wav")
    #missile sounds
fireMS = pygame.mixer.Sound("ricochet.wav")
hitMS = pygame.mixer.Sound("explosion.wav")
saveMS = pygame.mixer.Sound("shutter.wav")

#increments of the window
screenX = 1280
screenY = 800
halfX = screenX/2
halfY = screenY/2

screen = pygame.display.set_mode((screenX,screenY))
pygame.display.set_caption("Missile-Pong")

#define rectangles to make the shapes easy to draw
ball = pygame.Rect(halfX - 15, halfY - 15, 30, 30)
player1 = pygame.Rect(10, halfY, 10, 120)
player2 = pygame.Rect(screenX - 20, halfY, 10, 120)
missileP1 = pygame.Rect(-500, -500, 50, 30)
missileP2 = pygame.Rect(screenX+500, screenY+500, 50, 30)

#velocity
ballV_X = 15 * random.choice((1, -1))
ballV_Y = 15 * random.choice((1, -1))
player1VY = 0
player1VX = 0
player2VY = 0
player2VX = 0
missileP1V = 20
missileP2V = 20

#scoring increments
scoreP1 = 0
scoreP2 = 0
missileNumP1 = 0
missileNumP2 = 0
winScoreP1 = -1
winScoreP2 = -1

#table
def pongTable():
    
    #reference future variables
    global screenX, screenY, halfX, halfY, white, ball, player1, player2, missileP1, missileP2
    
    screen.fill(black)
    pygame.draw.rect(screen, white, player2, 2)
    pygame.draw.rect(screen, white, player1, 2)
    pygame.draw.ellipse(screen, red, ball)
    pygame.draw.aaline(screen, white, (0, 100), (screenX, 100))
    pygame.draw.aaline(screen, white, (halfX-halfX/2-50, 100), (halfX-halfX/2-50, screenY))
    pygame.draw.aaline(screen, white, (halfX+halfX/2+50, 100), (halfX+halfX/2+50, screenY))
    pygame.draw.aaline(screen, white, (halfX, 100), (halfX, screenY))
    pygame.draw.ellipse(screen, blue, missileP1)
    pygame.draw.ellipse(screen, blue, missileP2)

def tableText():
    
    #reference future variables
    global scoreP1, scoreP2, missileNumP1, missileNumP2, winScoreP1, winScoreP2
    
    #draw scoreboard text
    pointsP1 = fontSmall.render("Player 1: " + str(scoreP1), 1, white)
    pointsP2 = fontSmall.render("Player 2: " + str(scoreP2), 1, white)
    missileP1Text = fontSmall.render("Missiles: " + str(missileNumP1), 1, white)
    missileP2Text = fontSmall.render("Missiles: " + str(missileNumP2), 1, white)
    winScoreP1Text = fontSmall.render("Wins: " + str(winScoreP1), 1, white)
    winScoreP2Text = fontSmall.render("Wins: " + str(winScoreP2), 1, white)
    title = fontBig.render("Missile-Pong!", 1, white)

    #display text
    screen.blit(pointsP1, (25, 20))
    screen.blit(pointsP2, (1075, 20))
    screen.blit(title, (halfX - 170, 30))
    screen.blit(missileP1Text, (25, 60))
    screen.blit(missileP2Text, (1075, 60))
    if winScoreP1 >= 1:
        screen.blit(winScoreP1Text, (250, 30))
    if winScoreP2 >= 1:
        screen.blit(winScoreP2Text, (850, 30))

#ball movement
def ballTab():
    
    #reference the variables
    global ballV_X, ballV_Y, scoreP2, scoreP1, missileNumP2, missileNumP1
    
    #set position
    ball.x += ballV_X
    ball.y += ballV_Y
    
    #if ball hits the top or bottom of the screen
    if ball.top <= 100 or ball.bottom >= screenY:
        ballV_Y *= -1
        
    #if player 1 misses the ball
    if ball.left <= 0:
        ballMid()
        scoreP2 += 1
        if scoreP2 < 15 and scoreP2 % 5 == 0: #for every 5th increment in the score, the player will be given a missile
            missileNumP2 += 1
        pygame.mixer.Sound.play(lossS)

    #if player 2 misses the ball
    if ball.right >= screenX:
        ballMid()
        scoreP1 += 1
        if scoreP1 < 15 and scoreP1 % 5 == 0: #for every 5th increment in the score, the player will be given a missile
            missileNumP1 += 1
        pygame.mixer.Sound.play(lossS)

    #if the ball is caught by either of the players
    if ball.colliderect(player2) or ball.colliderect(player1):
        ballV_X *= -1
        pygame.mixer.Sound.play(pointS)

#player1 boundaries
def player1Tab():
    
    #y movements
    player1.y += player1VY
    if player1.top<= 100:
        player1.top = 100
    if player1.bottom >= screenY:
        player1.bottom = screenY
        
    #x movements
    player1.x += player1VX
    if player1.left<= 10:
        player1.left = 10
    if player1.right >= halfX-halfX/2-50:
        player1.right = halfX-halfX/2-50
    
#player2 boundaries
def player2Tab():
    
    #y movements
    player2.y += player2VY
    if player2.top<= 100:
        player2.top = 100
    if player2.bottom >= screenY:
        player2.bottom = screenY
        
    #x movements
    player2.x += player2VX
    if player2.left<= halfX+halfX/2+50:
        player2.left = halfX+halfX/2+50
    if player2.right >= screenX-20:
        player2.right = screenX-20


#repositioning the ball after it has been missed
def ballMid():
    
    #reference future variables
    global ballV_X, ballV_Y
    
    ball.center = (halfX, halfY + 100)
    ballV_Y += random.choice((1, -1))
    ballV_X += random.choice((1, -1))

#getting rid of the missile through its boundaries
def missileDestroy():
    
    #reference future variables
    global missileP1, missileP1V, missileP2, missileP2V, winScoreP1, winScoreP2
    
    #player 1 missile destroyed
    if missileP1.colliderect(player2):
        missileP1V = 0
        missileP1.center = (-500, -500)
        pygame.mixer.Sound.play(saveMS)
        
    if missileP1.colliderect(missileP2):
        missileP1V = 0
        missileP1.center = (-500, -500)
        pygame.mixer.Sound.play(saveMS)
        
    #player 2 missile destroyed
    if missileP2.colliderect(player1):
        missileP2V = 0
        missileP2.center = (screenX+500, screenY)
        pygame.mixer.Sound.play(saveMS)
        
    if missileP2.colliderect(missileP1):
        missileP2V = 0
        missileP2.center = (screenX+500, screenY)
        pygame.mixer.Sound.play(saveMS)
        
    #wins
    if missileP1.right >= screenX:
        missileP1V = 0
        missileP1.center = (-500, screenY)
        if winScoreP1 >= 1:
            pygame.mixer.Sound.play(hitMS)
        
    if missileP2.left <= 0:
        winScoreP2 += 1
        missileP2V = 0
        missileP2.center = (screenX+500, screenY)
        if winScoreP2 >= 1:
            pygame.mixer.Sound.play(hitMS)
        
#supplying player with win and resetting the board
def scoreReset():
    
    #reference future variables
    global scoreP1, winScoreP1, scoreP2, winScoreP2, missileNumP1, missileNumP2
    
    #refreshing it after 15 points
    if scoreP1 == 15:
        winScoreP1 += 1
        scoreP1 = 0
        scoreP2 = 0
        missileNumP1 = 0
        missileNumP2 = 0
    if scoreP2 == 15:
        winScoreP2 += 1
        scoreP2 = 0
        scoreP1 = 0
        missileNumP2 = 0
        missileNumP1 = 0
        

#game loop for game
while True:
    
    #introducing all events
    for event in pygame.event.get():
        
        #methods to quit the game
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_ESCAPE:
                pygame.quit()
                sys.exit()
            
        #player1 side commands
        if event.type == pygame.KEYDOWN:
            #up/down
            if event.key == pygame.K_s:
                player1VY += 20
            if event.key == pygame.K_w:
                player1VY -= 20
            #side to side
            if event.key == pygame.K_d:
                player1VX += 20
            if event.key == pygame.K_a:
                player1VX -= 20
                
            #missile intro
            if event.key == pygame.K_q:
                if missileNumP1 > 0:
                    missileNumP1 -=1
                    pygame.mixer.Sound.play(fireMS)
                    missileP1V = 20
                    missileP1.center = (player1.x, player1.y + 60)
                    
        #cancels movement when key isn't held              
        if event.type == pygame.KEYUP:
            #up/down
            if event.key == pygame.K_s:
                player1VY -= 20
            if event.key == pygame.K_w:
                player1VY += 20
            #side to side
            if event.key == pygame.K_d:
                player1VX -= 20
            if event.key == pygame.K_a:
                player1VX += 20
 
        #player2 side commands
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_DOWN:
                player2VY += 20
            if event.key == pygame.K_UP:
                player2VY -= 20
            if event.key == pygame.K_RIGHT:
                player2VX += 20
            if event.key == pygame.K_LEFT:
                player2VX -= 20
                
            #missile intro
            if event.key == pygame.K_RSHIFT:
                if missileNumP2 > 0:
                    pygame.mixer.Sound.play(fireMS)
                    missileNumP2 -=1
                    missileP2V = 20
                    missileP2.center = (player2.x, player2.y + 60)
                    
        #cancels movement when key isn't held  
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_DOWN:
                player2VY -= 20
            if event.key == pygame.K_UP:
                player2VY += 20
            if event.key == pygame.K_RIGHT:
                player2VX -= 20
            if event.key == pygame.K_LEFT:
                player2VX += 20
        
    #movement
    ballTab()
    player1Tab()
    player2Tab()
    
    #missile movement
    missileP1.x +=  missileP1V
    missileP2.x -= missileP2V
    missileDestroy()
        
    #table's written graphics/design
    pongTable()
    tableText()
    scoreReset()
    
    #update the window
    pygame.display.flip()
    clock.tick(60) #fps