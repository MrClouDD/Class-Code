class session_manager:
    game_library = set()
    session_list = []
    total_hours = {}
    
    def __init__(self, name, hours_played=0):
        self.name = name
        self.hours_played = hours_played
        self.add_session(name, hours_played)
    
    @classmethod
    def add_session(cls, input_name, input_hours) -> None:
        
        if input_name not in cls.game_library:
            cls.game_library.add(input_name)
        cls.session_list.append((input_name, input_hours))

    @classmethod
    def list_games(cls):
        sorted_games = sorted(cls.game_library)
        count = [x + 1 for x in range(len(sorted_games))]
        
        full_list = list(zip(count, sorted_games))
        
        for num, game in full_list:
            print(f"{num}) {game}")
        print()
            
    @classmethod
    def total_by_hours(cls):
        cls.total_hours.clear()
        
        for session in cls.session_list:
            name = session[0]
            hours = session[1]
            
            if name in cls.total_hours:
                cls.total_hours[name] = cls.total_hours[name] + hours
            else:
                cls.total_hours[name] = hours
                
        sorted_total_hours = sorted(cls.total_hours.items(), key=lambda x: x[1], reverse=True)

        for total in sorted_total_hours:
            print(f"Name: {total[0]:<20} | Total hours: {total[1]:>2}")
        print()

first_session = session_manager("Destiny 2", 30)

second_session = session_manager("The Division", 30)

third_session = session_manager("Destiny 2", 30)

session_manager.list_games()

session_manager.total_by_hours()